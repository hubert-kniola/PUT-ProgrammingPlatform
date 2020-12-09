from sqlalchemy.orm import sessionmaker, relationship
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String, ForeignKey, Table, CheckConstraint

import os

_Base = declarative_base()
_session_maker = None
_Session = None


class PhoneNumber(_Base):
    __tablename__ = 'phone_numbers'

    id = Column(Integer, primary_key=True)
    person_id = Column(Integer, ForeignKey('people.id'))
    person = relationship("Person", back_populates='phone_numbers')
    number = Column(String)

    @staticmethod
    def find(id): return _Session.query(PhoneNumber).filter(PhoneNumber.id == id).first()

    @staticmethod
    def add_new(person, number):
        obj = PhoneNumber(number=number, person=person)
        _Session.add(obj)
        _Session.commit()
        return obj

    def __repr__(self):
        return f"<PhoneNumber(id={self.id}, number={self.number}, person_id={self.person_id})>"


class Address(_Base):
    __tablename__ = 'addresses'

    id = Column(Integer, primary_key=True)
    person_id = Column(Integer, ForeignKey('people.id'))
    person = relationship("Person", back_populates='addresses')
    city = Column(String)
    street = Column(String)
    house_nr = Column(String)
    postal_code = Column(String)

    @staticmethod
    def find(id): return _Session.query(Address).filter(Address.id == id).first()

    @staticmethod
    def add_new(person, city, street, house_nr, postal_code):
        obj = Address(city=city, street=street, house_nr=house_nr, postal_code=postal_code, person=person)
        _Session.add(obj)
        _Session.commit()
        return obj

    def __repr__(self):
        return f"<Address(id={self.id}, city={self.city}, street={self.street}, house_nr={self.house_nr}, " \
               f"postal_code={self.postal_code}, person_id={self.person_id})>"


class Email(_Base):
    __tablename__ = 'emails'

    id = Column(Integer, primary_key=True)
    person_id = Column(Integer, ForeignKey('people.id'))
    person = relationship("Person", back_populates='emails')
    email = Column(String)

    @staticmethod
    def find(id): return _Session.query(Email).filter(Email.id == id).first()

    @staticmethod
    def add_new(person, email):
        obj = Email(email=email, person=person)
        _Session.add(obj)
        _Session.commit()
        return obj

    def __repr__(self):
        return f"<Email(id={self.id}, email={self.email}, person_id={self.person_id})>"


table_of_connection = Table('person_connections', _Base.metadata,
                            Column('first_personid', ForeignKey('people.id'), primary_key=True),
                            Column('second_personid', ForeignKey('people.id'), primary_key=True),
                            CheckConstraint('first_personid != second_personid'))


class Person(_Base):
    __tablename__ = 'people'

    id = Column(Integer, primary_key=True)
    name = Column(String)
    full_name = Column(String)
    phone_numbers = relationship("PhoneNumber", back_populates="person")
    emails = relationship("Email", back_populates="person")
    addresses = relationship("Address", back_populates="person")
    connections = relationship('Person', secondary=table_of_connection,
                               primaryjoin=table_of_connection.c.first_personid == id,
                               secondaryjoin=table_of_connection.c.second_personid == id,
                               back_populates='connections')

    @staticmethod
    def find(id):
        return _Session.query(Person).filter(Person.id == id).first()

    @staticmethod
    def add_new(name, full_name):
        obj = Person(name=name, full_name=full_name)
        _Session.add(obj)
        _Session.commit()
        return obj

    def create_connection(self, other):
        self.connections.append(other)
        other.connections.append(self)
        _Session.commit()

    @staticmethod
    def find_where(names=None, full_names=None):
        base = _Session.query(Person)

        if names:
            base = base.filter(Person.name.in_(names))

        if full_names:
            base = base.filter(Person.full_name.in_(full_names))

        return base.all()

    def __repr__(self):
        return f"<Person(id={self.id}, name={self.name}, full_name={self.full_name}" + \
               f", numbers_len={len(self.phone_numbers)}, addresses_len={len(self.addresses)}" + \
               f", emails_len={len(self.emails)}, connections_len={len(self.connections)})>"


def create_db(engine, drop=False):
    if drop:
        try:
            os.remove(f'./{engine.url.database}')
        except FileNotFoundError:
            pass
    _Base.metadata.create_all(engine)


def session_creator(engine):
    global _session_maker
    global _Session

    if _session_maker is None:
        _session_maker = sessionmaker(bind=engine)
        _Session = _session_maker()

    return _Session

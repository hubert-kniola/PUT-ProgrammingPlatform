from sqlalchemy import create_engine
from database import *

#Zadanie 1

engine = create_engine('sqlite:///users.db')
create_db(engine, drop=True)

#Zadanie 2/3

session = session_creator(engine)

person1 = Person.add_new('Jacob', 'Parker')
phone1 = PhoneNumber.add_new(person1, '555666777')
phone12 = PhoneNumber.add_new(person1, '777666555')
address1 = Address.add_new(person1, 'Londyn', 'Carington', '2', '12-345')
email1 = Email.add_new(person1, 'jacpar@mail.com')

person2 = Person.add_new('James', 'Smith')
phone2 = PhoneNumber.add_new(person2, '111222333')
address2 = Address.add_new(person2, 'Norton', 'Wexel', '14', '890')
email2 = Email.add_new(person2, 'smijames123@mail.com')

person3 = Person.add_new('Markus', 'Kirkman')
phone3 = PhoneNumber.add_new(person3, '444888999')
address3 = Address.add_new(person3, 'Cambridge', 'Warzone', '111', '22-22-22')
email3 = Email.add_new(person3, 'markuskirkman@mail.com')

person4 = Person.add_new('Thomas', 'James')
phone4 = PhoneNumber.add_new(person4, '333222111')
address4 = Address.add_new(person4, 'Oxford', 'Redindent', '33b', '45-44')
email4 = Email.add_new(person4, 'thojam222@mail.com')

person1.create_connection(person2)
person3.create_connection(person4)

print(f"{person1=}")
print(f"{person2=}")
print(f"{person3=}")
print(f"{person4=}")


print(f"{person1.connections=}")
print(f"{person3.connections=}")

#Zadanie 4

person5 = Person.add_new('Ralph', 'Nazar')
phone5 = PhoneNumber.add_new(person5, '123456789')
address5 = Address.add_new(person5, 'Washington', 'Freedom', '45a', '55-555')
email5 = Email.add_new(person5, 'thojam222@mail.com')
people = Person.find_where(names=['Ralph'])

print(people)

phone = PhoneNumber.find(1)
mail = Email.find(1)
address = Address.find(1)

print(phone, mail, address)
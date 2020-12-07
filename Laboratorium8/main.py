from sqlalchemy import create_engine, Column, Integer, String


engine = create_engine("sqlite:///users.db")

def print_hi(name):
    print(f'Hi, {name}')



if __name__ == '__main__':
    print_hi('PyCharm')
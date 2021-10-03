import petname
import random
from faker import Faker  # generate fake data
import json


class Owner:
    def __init__(self, name, address, birthdate, telephone, id, pets):
        self.name = name
        self.address = address
        self.birthdate = birthdate
        self.telephone = telephone
        self.id = id
        self.pets = pets

    def encode(self):
        return self.__dict__


class Pet:
    def __init__(self, name, specie, description, gender, id, weight):
        self.name = name
        self.specie = specie
        self.description = description
        self.gender = gender
        self.id = id
        self.weight = weight

    def encode(self):
        return self.__dict__


def generator(n, m, name):
    fake = Faker()
    owners = []
    for i in range(0, n):
        # PET
        pets = []
        for j in range(0, random.randint(1, m)):
            pets.append(Pet(petname.name(), petname.Generate(), petname.adjective(),
                            'female' if random.random() > 0.5 else 'male', random.randint(1, 100),
                            random.randint(1, 1000000)))

        # OWNER
        owners.append(Owner(fake.name(), fake.address(), fake.date(), fake.phone_number(),
                            random.randint(1, 1000000), pets))

    jsontracks = json.dumps(owners, default=lambda o: o.encode(), indent=4)
    with open(f'Ficha_1/test_files/{name}.json','w') as f:
        f.write(jsontracks)



for i in range(100):
    generator(random.randint(1, 100), random.randint(1, 15), f'owners{str(i+1)}')


from django.db import models
from django.contrib.auth.models import AbstractUser


class User(AbstractUser):
    username = models.CharField(max_length=20, blank=False, unique=True)
    email= models.EmailField(blank=False)

    def __str__(self) -> str:
        return self.username
    

class Calculator(models.Model):

    first_number = models.CharField(max_length=20, blank=False)
    operation = models.CharField(max_length=1, blank=False)
    second_number = models.CharField(max_length=20, blank=False)
    result = models.CharField(max_length=1024, blank=False)


class MostFrequentOperation(models.Model):

    value = models.CharField(max_length=1, blank=True)


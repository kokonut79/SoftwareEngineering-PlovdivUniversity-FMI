from django.db import models

# Create your models here.


class Bag(models.Model):
    name = models.CharField(max_length=200)
    
    def __str__(self):
        return self.name
        
class Deed(models.Model):
    bag = models.ForeignKey(Bag, on_delete=models.CASCADE)
    name = models.CharField(max_length=200)
    def __str__(self):
        return self.name
        
class Discipline(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    
    def __str__(self):
        return self.name

class Student(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    major = models.CharField(max_length=200)
    fn = models.CharField(max_length=200)
    
    def __str__(self):
        return self.name

class Student_Courses(models.Model):
    #should it have name field? 
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    discp = models.ForeignKey(Discipline, on_delete=models.CASCADE)
    






class Place(models.Model):
    adress = models.CharField('Name of the place', max_length=100)
    phone = models.CharField('Contact Phone', max_length=20)
    web = models.URLField('Website Address', max_length=500)

    def __str__(self): 
        return self.adress



class Fan(models.Model):
    fname = models.CharField(max_length=30)
    lname = models.CharField(max_length=60)

    def __str__(self):
        return self.fname + ' ' + self.lname


        
class Event(models.Model):
    name = models.CharField('Event Name', max_length=100)
    event_date = models.DateTimeField('Event Date')
    place = models.ForeignKey(Place, blank=True, null=True, on_delete=models.CASCADE)
    description = models.CharField(max_length=300)
    fans = models.ManyToManyField(Fan, blank= True)

    def __str__(self): 
        return self.name








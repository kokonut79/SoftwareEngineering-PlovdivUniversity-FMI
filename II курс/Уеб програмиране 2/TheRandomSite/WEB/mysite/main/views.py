from django.shortcuts import render
from django.http import HttpResponse
from .models import Deed, Student_Courses, Student, Event, Fan, Place, Bag, Discipline  #changed studen_course import
import random
from django.core import serializers
from django.contrib import messages

#Here we create more pages to the site

def index(request):
    if request.method == "POST":
        Bag.objects.all().delete()
        Discipline.objects.all().delete()
        Student.objects.all().delete()
        Fan.objects.all().delete()
        Place.objects.all().delete()
        messages.success(request, "You have successfully destroyed all records in the database!")
        

    return render(request, "main/index.html")
    
def goodDeeds(request):
    
    deedLen = Deed.objects.count()
    randomID = random.randint(1,deedLen)
    
    try:
        deeds = Deed.objects.get(id=randomID)
    except Deed.DoesNotExists:
        pass
    
    context = {
        "deeds":deeds,
    }
    
    return render(request, "main/goodDeeds.html",context)
    
def Harvard(request):
    
    data = serializers.serialize('python', Student.objects.all())
    context = {
        'data':data,
    }
    return render(request, "main/harvard.html", context)
    
    
def infoAdd(request):
    if request.method == "POST":
        name = request.POST['name']
        major = request.POST['major']
        fn = request.POST['fn']
        listOfErors = [] 
        
        if len(name) <= 5: 
            listOfErors.append("The student's name can't be less then or equal to 5 chars, please input his NAME and SURNAME!\n")
        if len(major) <= 1:
            listOfErors.append("The student's major can't be less then or euqal to 1char, please enter the full name of the major!\n")
        if len(fn) != 10:
            listOfErors.append("The student's faculty number should be equal to 10 numbers!")
            
        if (len(listOfErors) == 0):
            new_student = Student(name = name, major = major,fn = fn)
            new_student.save()
            messages.success(request, "The student was added successfully!")
        else:
            return HttpResponse(listOfErors)
               
        
    return render(request, "main/infoAdd.html")
    
def NoPermission(request):
    return render(request, "main/NoPermission.html")





def addFan(request):
    if request.method == "POST":
        fname = request.POST['fname']
        lname = request.POST['lname']
        
        listOfErors = [] 
        
        if len(fname) < 2: 
            listOfErors.append("FNAME TOO SHORT\n")
        if len(lname) <= 3:
            listOfErors.append("LNAME TOO SHORTT\n")
       
            
        if (len(listOfErors) == 0):
            new_fan = Fan(fname = fname, lname = lname)
            new_fan.save()
        else:
            return HttpResponse(listOfErors)
               
        
    return render(request, "main/addFan.html")


def all_events(request):
    event_list= Event.objects.all()
    return render(request, 'main/events.html', {'event_list' : event_list})









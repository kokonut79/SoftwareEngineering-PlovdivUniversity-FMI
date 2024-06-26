from django.contrib import admin
from .models import Bag, Deed, Discipline, Student, Student_Courses, Place, Fan, Event 
# Register your models here.

admin.site.register(Bag)
admin.site.register(Deed)
admin.site.register(Discipline)
admin.site.register(Student)
admin.site.register(Student_Courses)
#admin.site.register(Photo)
#admin.site.register(Photographer)
admin.site.register(Place)
admin.site.register(Fan)
admin.site.register(Event)

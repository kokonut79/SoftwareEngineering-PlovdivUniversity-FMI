from django.urls import path
from . import views

urlpatterns = [
path("", views.index, name="index"),
path("goodDeeds/", views.goodDeeds, name="good deed"),
path("harvard/", views.Harvard, name="Harvard"),
path("infoAdd/", views.infoAdd, name="infoAdd"),
path("NoPermission/", views.NoPermission, name="NoPermission"),
path("addFan/", views.addFan, name="Thanks for applying"),
path('events/', views.all_events, name="events" ),

]
#       ^      ^                        ^
#       |      |                        |
#  HowToSearchForIT, callIT,            and its name
from django.contrib import admin
from .models import User, Calculator, MostFrequentOperation


@admin.register(User)
class UserAdmin(admin.ModelAdmin):
    search_fields = ('username', 'password', 'email')


@admin.register(Calculator)
class CalculatorAdmin(admin.ModelAdmin):
    search_fields = ('first_number', 'operation', 'second_number', 'result')
    list_display = ('first_number', 'operation', 'second_number', 'result')


@admin.register(MostFrequentOperation)
class MostFrequentOperationAdmin(admin.ModelAdmin):
    search_fields = ('value',)
    list_display = ('value',)
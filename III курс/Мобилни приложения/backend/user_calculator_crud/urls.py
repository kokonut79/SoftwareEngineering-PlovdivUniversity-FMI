from django.urls import path
from user_calculator_crud.views import UserView, CalculatorView, LoginView, CalculatorGetResultView



urlpatterns = [
    path('user/', UserView.as_view()),
    path('login/', LoginView.as_view()),
    path('calculator/', CalculatorView.as_view()),
    path('getresult/', CalculatorGetResultView.as_view()),
]
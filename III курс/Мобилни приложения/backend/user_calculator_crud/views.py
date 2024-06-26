from rest_framework.views import APIView
from rest_framework import status
from user_calculator_crud.user_logic import create_user, get_user,  update_user, delete_account, login_user
from user_calculator_crud.calculator_logic import calculate, get_most_frequent_operation, get_the_newest_result
from django.http import JsonResponse


class UserView(APIView):
    """User register"""

    def post(self, request):
        return handle_response(create_user(request))
    
    def get(self, request):
        return handle_response_data(get_user(request))
    
    def put(self, request):
        return handle_response(update_user(request))
    
    def delete(self, request):
        return handle_response(delete_account(request))
    
class LoginView(APIView):
    def post(self, request):
        return handle_response(login_user(request))


class CalculatorView(APIView):

    def post(self, request):
        return handle_response(calculate(request))
    
    def get(self, request):
        return handle_response(get_most_frequent_operation())


class CalculatorGetResultView(APIView):
    def get(self, request):
        return handle_response(get_the_newest_result(request))


# ______ SUPP ______ #
def handle_response(msg):
    if isinstance(msg, dict) and 'Error' in msg.keys():
        return JsonResponse(data=msg, status=status.HTTP_400_BAD_REQUEST)
    else:
        # If the obj is type set return list(obj)
        if isinstance(msg, set):
            return JsonResponse(data=list(msg), status=status.HTTP_200_OK, safe=False)
        else:
            return JsonResponse(data=msg, status=status.HTTP_200_OK, safe=False)

def handle_response_data(msg):
    if isinstance(msg, dict) and 'Error' in msg.keys():
        return JsonResponse(data=msg, status=status.HTTP_200_OK, safe=False)
    else:
        return JsonResponse(data=msg.data, status=status.HTTP_200_OK)
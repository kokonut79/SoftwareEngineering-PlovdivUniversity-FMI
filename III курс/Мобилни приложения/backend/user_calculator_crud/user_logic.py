from user_calculator_crud.models import User
from user_calculator_crud.serializers import UserSerializerSearchByUsername
from django.core.exceptions import MultipleObjectsReturned
from django.contrib.auth.hashers import make_password
from django.contrib.auth import authenticate


#create read update delete
def create_user(request) -> dict:
    """User register method"""

    # data request
    username = request.data.get('username')
    email = request.data.get('email')
    password = request.data.get('password')
    password_check = request.data.get('password_check')
    
    # validate email uniqness
    if User.objects.filter(email=email).exists():
        return {'This email is already in use!'}
    # validate username uniqueness
    if User.objects.filter(username=username).exists():
        return {'Error': 'the username is already in use!'}
    # validate email len
    if len(str(email))==0:
        return {'Error': 'Email was not provided!'}
    # validate username len
    if len(username) <= 2:
        return {'Error', f'the username: {username} is too short'}
    # validate password
    if len(password) < 8:
        return {'Error': 'the password is too short!'}
    if password != password_check:
        return {'Error': 'the passwords do not match!'}

    # convert the pass to hash
    hashed_password = make_password(password)

    # create user
    user = User(
        username=username,
        email=email,
        password=hashed_password,
        is_active=True
    )
    user.save()
    info = {'Success': 'The account was created!'}

    return info


def get_user(request):

    username = request.data.get('username')

    try:
        user = User.objects.get(username=username)
        serialized = UserSerializerSearchByUsername(user)
    except User.DoesNotExist:
        return {'Error': 'The user does not exist!'}
    except MultipleObjectsReturned:
        return {'Error': 'The user does not exist!'}
    
    return serialized


def update_user(request): 

    username = request.data.get('username')
    wanted_username = request.data.get('wanted_username')

    try:
        user_obj = User.objects.get(username=username)
    except (MultipleObjectsReturned, User.DoesNotExist):
        return {'Error': 'The user could not be found!'}
    
    if User.objects.filter(username=wanted_username).exists():
        return {'Error': 'This username is already in use!'}
    else:
        user_obj.username=wanted_username
        user_obj.save()

    info = {'Success': 'changed the usename!'}

    return info


def delete_account(request):

    account_to_delete = request.data.get('username')

    try:
        user_obj = User.objects.get(username=account_to_delete)
    except User.DoesNotExist:
        return {'Error': 'The user does not exist!'}
    except MultipleObjectsReturned:
        return {'Error': 'The user does not exist!'}
    
    user_obj.delete()
    info = {'Success': 'The account was deleted'}

    return info


def login_user(request) -> dict:
    """Login method, returns the header token"""
    username = request.data.get('username')
    password = request.data.get('password')
    ERROR = {'Error': 'Invalid credintials!'}

    try:
        user_obj = User.objects.get(username=username)
    except User.DoesNotExist:
        return ERROR

    flag = authenticate(request, username=username, password=password)
    if flag:
        return {'Success': 'logged in!'}
    else:
        return ERROR


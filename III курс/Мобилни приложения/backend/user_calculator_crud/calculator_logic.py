from user_calculator_crud.models import Calculator, MostFrequentOperation


def check_the_highest_number(a: int, b: int, c: int, d: int) -> int:
    list_of_numbers = list()
    list_of_numbers.append(a)
    list_of_numbers.append(b)
    list_of_numbers.append(c)
    list_of_numbers.append(d)
    list_of_numbers.sort(reverse=True)

    greatest = list_of_numbers[0]
    pretender = list_of_numbers[1]
    if greatest == pretender:
        return 222222222222

    return greatest


def calculate(request) -> float:

    first_num = request.data.get('first_num')
    operation = request.data.get('operation')
    second_num = request.data.get('second_num')


    first_num_float = float(first_num)
    second_num_float = float(second_num)
    result = 0
    
    match operation:
        case '+':
            result = first_num_float + second_num_float
            entry = Calculator(
                first_number=first_num,
                operation=operation,
                second_number=second_num,
                result=result
            )
            entry.save()
            MostFrequentOperation.objects.create(value=operation)
        
        case '-':
            result = first_num_float - second_num_float
            entry = Calculator(
                first_number=first_num,
                operation=operation,
                second_number=second_num,
                result=result
            )
            entry.save()
            MostFrequentOperation.objects.create(value=operation)

        case '*':
            result = first_num_float * second_num_float
            entry = Calculator(
                first_number=first_num,
                operation=operation,
                second_number=second_num,
                result=result
            )
            entry.save()
            MostFrequentOperation.objects.create(value=operation)
        
        case '/':
            if first_num_float == 0 or second_num_float == 0:
                result = 0
            else:
                result = first_num_float / second_num_float
            entry = Calculator(
                first_number=first_num,
                operation=operation,
                second_number=second_num,
                result=result
            )
            entry.save()
            MostFrequentOperation.objects.create(value=operation)
        case _:
            result = 0.0000
        
    return {'Success': 'the calculation completed'}

def get_most_frequent_operation():

    add = MostFrequentOperation.objects.filter(value='+').count()
    substract = MostFrequentOperation.objects.filter(value='-').count()
    multiplication = MostFrequentOperation.objects.filter(value='*').count()
    division = MostFrequentOperation.objects.filter(value='/').count()

    most_used_operation = check_the_highest_number(add, substract, multiplication, division)

    if most_used_operation == add:
        return 'The most used operation is +'
    elif most_used_operation==substract:
        return 'The most used operation is -'
    elif most_used_operation==multiplication:
        return 'The most used operation is *'
    elif most_used_operation == division:
        return 'The most used operation is /'
    elif most_used_operation == 222222222222:
        return 'There are multiple operations which are used / or not used - the same number of times!'
    else:
        return {'Error':'An error occured!'}
    

def get_the_newest_result(request):

    result_obj = Calculator.objects.filter().last();
    result = result_obj.result
    return result

    

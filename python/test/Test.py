import re

def scope_test():
    def do_local():
        spam = "local spam"

    def do_nonlocal():
        nonlocal spam
        spam = "nonlocal spam"

    def do_global():
        global spam
        spam = "global spam"

    spam = "test spam"
    do_local()
    print("After local assignment:", spam)
    do_nonlocal()
    print("After nonlocal assignment:", spam)
    do_global()
    print("After global assignment:", spam)

scope_test()
print("In global scope:", spam)

s='10000011111100011010101011'

s2 = '010'
n = 3

my_regex = r"((0){1," + str(n) + r"}|(1){1," + str(n) + "})\1*?"

l = [m.group() for m in re.finditer(my_regex, s2)]


print("out", l)
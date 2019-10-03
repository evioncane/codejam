testCases = int(input())

for testCase in range(1, testCases + 1):

    number = str(input())
    first_num = ""
    second_num = ""

    for i in range (len(number)):
        if (number[i] == '4'):
            first_num += '3'
            second_num += '1'
            continue
        second_num += '0'
        first_num += number[i]

    print("Case #" + str(testCase) + ": "
          + str(first_num)
          +" "+ str(int(second_num)), flush=True)
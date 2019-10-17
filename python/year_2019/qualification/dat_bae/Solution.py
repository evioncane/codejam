import re
import math


def switch_bit(bit):
    if (bit == '0'):
        return '1'
    elif (bit == '1'):
        return '0'


def get_results(node):
    global count
    global results
    if len(node.children) > 0:
        for child in node.children:
            get_results(child)
    elif node.not_working == 0:
        count += node.length
    elif node.length == node.not_working:
        for i in range(0, node.length):
            results.append(count)
            count = count + 1


class Node(object):

    def __init__(self, length, not_working):
        self.length = int(length)
        self.not_working = int(not_working)
        self.children = []

    def add_child(self, obj):
        self.children.append(obj)

    def generate_node_input(self):
        bit = '1'
        inp = ""
        if (self.children):
            for child in self.children:
                inp += child.generate_node_input()
        elif self.not_working == 0:
            for i in range(0, int(self.length)):
                inp += '0'
        else:
            for i in range(0, int(self.length)):
                if (i % int(self.not_working) == 0):
                    bit = switch_bit(bit)
                inp += bit
        return inp


    def generate_child_nodes(self, output):
        global check
        if self.length == 1 or self.not_working == 0 or self.length == self.not_working:
            return
        elif len(self.children) > 0:
            start = int(0)
            for child in self.children:
                output_length = int(child.length - child.not_working)
                end = int(start + output_length)
                mini_output = output[start: end]
                child.generate_child_nodes(mini_output)
                start = start + output_length
        elif int(self.length) > 1 and int(self.not_working) > 0:
            check = False
            child_node_number = int(self.length / self.not_working)
            my_regex = r"((0){1," + str(self.not_working) + r"}|(1){1," + str(self.not_working) + "})\1*?"
            list = [m.group() for m in re.finditer(my_regex, output)]
            excpected_character = '0'
            all_in_one_node = False
            for i in range(0, child_node_number):
                if len(list) > i:
                    chr = list[i][0]
                # calculate not_working
                if chr and chr == excpected_character:
                    problematic_bits = self.not_working - len(list[i])
                    node = Node(self.not_working, problematic_bits)
                    self.add_child(node)
                    excpected_character = switch_bit(excpected_character)
                else:
                    node = Node(self.not_working, self.not_working)
                    self.add_child(node)
                    node2 = Node(self.not_working, int(0))
                    self.add_child(node2)
                    excpected_character = switch_bit(excpected_character)
                    #all_in_one_node = True
            last_node_length = self.length % self.not_working
            chr = list[-1][0]
            if last_node_length != 0:
                if chr == excpected_character:
                    last_node_not_working = last_node_length - len(list[-1])
                    last_node = Node(last_node_length, last_node_not_working)
                    self.add_child(last_node)
                else:
                    previous_node = Node(last_node_length, last_node_length)
                    self.add_child(previous_node)


testCases = int(input())

for testCase in range(1, testCases + 1):
    line = input().split()
    n = line[0]
    b = line[1]
    f = line[2]
    parentNode = Node(n, b)
    for i in range(0, 5):
        check = True
        # generate input
        input_param = parentNode.generate_node_input()
        print(input_param, flush=True)
        # get output
        output = input()
        # modify tree
        parentNode.generate_child_nodes(output)
        if check:
            global results
            results = []
            global count
            count = 0
            get_results(parentNode)
            print(' '.join(map(str, results)), flush=True)
            break

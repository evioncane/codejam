import re
import math

def switch_bit(bit):
    if (bit == '0'):
        return '1'
    elif (bit == '1'):
        return '0'

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
        else:
            for i in range(0, int(self.length)):
                if (i%int(self.not_working)==0):
                    bit = switch_bit(bit)
                inp += bit
        return inp

    def generate_child_nodes(self, output):
        check = True
        if self.length == 1 or self.not_working == 0 or self.length == self.not_working:
            return
        elif len(self.children) > 0:
            start = int(0)
            for child in self.children:
                output_length = int(child.length-child.not_working)
                mini_output = output[start, start+output_length]
                child.generate_node_input(mini_output)
                start = start + output_length
        elif int(self.length) > 1 and int(self.not_working) > 0:
            child_node_number = math.ceil(self.length/self.not_working)
            my_regex = r"((0){1," + str(self.not_working) + r"}|(1){1," + str(self.not_working) + "})\1*?"
            list = [m.group() for m in re.finditer(my_regex, output)]
            excpected_character = '0'
            all_in_one_node = False
            for i in range(0, child_node_number):
                chr = list[i]
                #calculate not_working
                if chr == excpected_character:
                    problematic_bits = self.not_working-len(list)
                    node = Node(self.not_working, problematic_bits)
                    self.add_child(node)
                    excpected_character = switch_bit(excpected_character)
                else:
                    node = Node(self.not_working, self.not_working)
                    self.add_child(node)
                    all_in_one_node = True
                    break
            if not all_in_one_node:
                last_node_length = self.length % self.not_working
                if last_node_length != 0:
                    last_node_not_working = last_node_length - len(list[-1])
                    last_node = Node(last_node_length, last_node_not_working)
                    self.add_child(last_node)



    #def check_if_done(self):



testCases = int(input())

for testCase in range(1, testCases + 1):
    line = input().split()
    n = line[0]
    b = line[1]
    f = line[2]
    parentNode = Node(n, b)
    #node1 = Node(11,5)
    #node2 = Node(3,1)
    #node3 = Node(6,3)
    #node4 = Node(5,2)
    #parentNode.add_child(node1)
    #parentNode.add_child(node2)
    #parentNode.children[0].add_child(node3)
    #parentNode.children[0].children[0].add_child(node4)
    inp = parentNode.generate_node_input()
    for i in range(0, 5):
        #generate input
        input_param = parentNode.generate_node_input()
        print(input_param, flush=True)
        #get output
        output = input()
        #modify tree
        parentNode.generate_child_nodes(output)
        print("kico")
        #check if done
        #result = parentNode.check_if_done()
        #if (result):
        #    print(str(result), flush=True)
        #    break
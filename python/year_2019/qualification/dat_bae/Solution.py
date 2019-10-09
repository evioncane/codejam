def switch_bit(bit):
    if (bit == '0'):
        return '1'
    elif (bit == '1'):
        return '0'

class Node(object):

    def __init__(self, length, not_working):
        self.length = length
        self.not_working = not_working
        self.children = []

    def add_child(self, obj):
        self.children.append(obj)

    def generate_node_input(self):
        bit = '1'
        input = ""
        if (self.children):
            for child in self.children:
                input += child.generate_node_input()
        else:
            for i in range(0, int(self.length)):
                if (i%int(self.not_working)==0):
                    bit = switch_bit(bit)
                input += bit
        return input

    def generate_child_nodes(self, output):
        if (self.length == 1):
            return
        #if (self.not_working > 0 and self.children):



    #def check_if_done(self):



testCases = int(input())

for testCase in range(1, testCases + 1):
    line = input().split()
    n = line[0]
    b = line[1]
    f = line[2]
    parentNode = Node(n, b)
    node1 = Node(11,5)
    node2 = Node(3,1)
    node3 = Node(6,3)
    node4 = Node(5,2)
    parentNode.add_child(node1)
    parentNode.add_child(node2)
    parentNode.children[0].add_child(node3)
    parentNode.children[0].children[0].add_child(node4)
    inp = parentNode.generate_node_input()
    for i in range(0, 5):
        #generate input
        input_param = parentNode.generate_node_input()
        print(input_param, flush=True)
        #get output
        output = input()
        #modify tree
        parentNode.generate_child_nodes(output)
        #check if done
        result = parentNode.check_if_done()
        if (result):
            print(str(result), flush=True)
            break
import re


def switch_bit(bit):
    if bit == '0':
        return '1'
    elif bit == '1':
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
        if self.children:
            for child in self.children:
                inp += child.generate_node_input()
        elif self.not_working == 0:
            for i in range(0, int(self.length)):
                inp += '0'
        else:
            for i in range(0, int(self.length)):
                if i % int(self.not_working) == 0:
                    bit = switch_bit(bit)
                inp += bit
        return inp

    def generate_child_nodes(self, output):
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
            child_node_number = int(self.length / self.not_working)
            my_regex = r"((0){1," + str(self.not_working) + r"}|(1){1," + str(self.not_working) + "})\1*?"
            grouped_by_nodes = [m.group() for m in re.finditer(my_regex, output)]
            expected = '0'
            i = 0
            while i < child_node_number:
                if len(grouped_by_nodes) > i:
                    actual_char = grouped_by_nodes[i][0]
                if actual_char and actual_char == expected:
                    problematic_bits = self.not_working - len(grouped_by_nodes[i])
                    node = Node(self.not_working, problematic_bits)
                    self.add_child(node)
                    expected = switch_bit(expected)
                else:
                    node = Node(self.not_working, self.not_working)
                    self.add_child(node)
                    if len(grouped_by_nodes) > i:
                        node2 = Node(self.not_working, int(0))
                        self.add_child(node2)
                        child_node_number -= 1
                i += 1
            last_node_length = self.length % self.not_working
            actual_char = grouped_by_nodes[-1][0]
            if last_node_length != 0:
                if actual_char == expected:
                    last_node_not_working = last_node_length - len(grouped_by_nodes[-1])
                    last_node = Node(last_node_length, last_node_not_working)
                    self.add_child(last_node)
                else:
                    previous_node = Node(last_node_length, last_node_length)
                    self.add_child(previous_node)

    def check_if_solved(self):
        global results
        global count
        if self.not_working == 0:
            count += self.length
            return True
        elif self.length == self.not_working:
            for i in range(0, self.length):
                results.append(count)
                count += 1
            return True
        elif len(self.children) > 0:
            for child in self.children:
                result = child.check_if_solved()
                if not result:
                    return False
            return True
        return False


def main():
    global results
    global count
    test_cases = int(input())

    for testCase in range(1, test_cases + 1):
        solved = False
        line = input().split()
        n = line[0]
        b = line[1]
        f = line[2]
        parent_node = Node(n, b)
        for i in range(0, int(f)):
            results = []
            count = 0
            input_param = parent_node.generate_node_input()
            print(input_param, flush=True)
            output = input()
            parent_node.generate_child_nodes(output)
            if parent_node.check_if_solved():
                print(' '.join(map(str, results)), flush=True)
                test_result = int(input())
                if test_result == 1:
                    solved = True
                else:
                    raise Exception
                break
        if not solved:
            raise Exception


if __name__ == "__main__":
    main()

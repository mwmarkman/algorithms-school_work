import traceback, turtle
import collections


# Takes as input a Square object node in a graph of Square nodes.
# This will always be the Square node representing (0,0), the start position
# Performs BFS until the goal Square is found (the Square with val == 2).
# Returns a list containing each Square node in the path from the start
# (0,0) to the goal node, inclusive, in order from start to goal.
def find_path(start_node):
    start_node.set_color("gray")
    start_node.depth = 0
    start_node.prev = None
    my_lazy_q = []  # Use .append() to enqueue and .pop(0) to dequeue
    my_lazy_q.append(start_node)
    ret_list = []
    found_node = False
    while len(my_lazy_q) > 0 and found_node is False:
        j = my_lazy_q.pop(0)
        for i in j.adj:
            if i.get_color() is "white":
                i.set_color("gray")
                i.depth = j.depth + 1
                i.prev = j
                my_lazy_q.append(i)
            if i.val == 2:
                i.depth = j.depth + 1
                i.prev = j
                the_square = i
                while the_square.x != 0 or the_square.y != 0:
                    print(the_square)
                    ret_list.insert(0, the_square)
                    the_square = the_square.prev
                ret_list.insert(0, the_square)
                found_node = True
        if not found_node:
            j.set_color("black")
    return ret_list



import traceback


# huffmanEncode: Takes in a single String inputString, which is
# the string to be encoded.  Computes the optimal binary encoding
# of the string and encodes it, returning a String of 0s and 1s.
# This is not an actual Python binary string, just a normal String
# that happens to contain only 0's and 1's.
def huffmanEncode(inputString):
    # Count frequency of each character in string
    # and record it in a dictionary
    chDiction = {}
    for ch in inputString:
        if ch in chDiction:
            chDiction[ch] += 1
        else:
            chDiction[ch] = 1

    # Create a Min Priority Queue, populate it with nodes based
    # on letter frequency
    chQueue = []
    for ch in chDiction:
        newNode = ChrNode(ch, chDiction[ch])
        min_heap_insert(chQueue, newNode)

    # We will already have a min heap that is populated in correct order
    # when we pass this to the portion of the code below
    # the priority heap already contains the nodes we need to populate the tree with, at least the character values

    # Tree and use that to convert the string into a minimum
    # length string of only 0's and 1's.

    # Compute Huffman Binary Tree from min heap

    for i in range(1,len(chDiction)):  # This might need to be changed to n-1
        z = ChrNode("NA", -1)
        z.left = heap_extract_min(chQueue)
        z.right = heap_extract_min(chQueue)
        z.freq = z.left.freq + z.right.freq
        min_heap_insert(chQueue, z)

    # Create a dictionary with each character and its corresponding bitcode
    # Follow every root to leaf path and encode that value gained while you
    # follow the path to the dict

    tree = heap_extract_min(chQueue)
    my_dict = buildTable(tree, "", {}, len(chDiction))

    #parse the input string and create a new string containing the strings

    output = ""
    for i in inputString:
        output += my_dict[i]

    return output


def buildTable(node, string, encodeDict, length):
    if node is not None:
        if node.left is None and node.right is None:
            encodeDict[node.ch] = string
        buildTable(node.left, string + "0", encodeDict, length)
        buildTable(node.right, string + "1", encodeDict, length)
    return encodeDict




class ChrNode:
    def __init__(self, ch, freq, left=None, right=None):
        self.ch = ch
        self.freq = freq
        self.left = left
        self.right = right

    def __repr__(self):
        return self.ch + ":" + str(self.freq)



def min_heapify(Q, i):
    l = 2 * i + 1
    r = 2 * i + 2
    smallest = i
    if l < len(Q) and Q[l].freq < Q[smallest].freq:
        smallest = l
    if r < len(Q) and Q[r].freq < Q[smallest].freq:
        smallest = r
    if i != smallest:
        Q[i], Q[smallest] = Q[smallest], Q[i]
        min_heapify(Q, smallest)


def heap_extract_min(Q):
    if len(Q) == 1:
        return Q.pop()
    minElement = Q[0]
    Q[0] = Q.pop()
    min_heapify(Q, 0)
    return minElement


def parent(i):
    return int((i - 1) / 2)


def min_heap_insert(Q, node):
    Q.append(node)
    i = len(Q) - 1
    while i > 0 and Q[parent(i)].freq > Q[i].freq:
        Q[parent(i)], Q[i] = Q[i], Q[parent(i)]
        i = parent(i)




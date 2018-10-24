def parent(i):
    return int((i - 1) / 2)

def left(i):
    return 2 * i + 1


def right(i):
    return 2 * i + 2


def max_heapify(A, i):
    L = left(i)
    R = right(i)
    if L < A.heap_size and A[L].priority > A[i].priority:
        max_val = L
    else:
        max_val = i
    if R < A.heap_size and A[R].priority > A[max_val].priority:
        max_val = R
    if max_val != i:
        holder = A[i]
        A[i] = A[max_val]
        A[max_val] = holder
        max_heapify(A,max_val)


def build_max_heap(A):
    A.heap_size = len(A)
    for i in range(int(len(A) / 2) - 1, -1, -1):
        max_heapify(A, i)

def heap_maximum(A):
    return A[0]


def heap_extract_max(A):
    if A.heap_size < 1:
        return None
    max_val = A[0]
    A[0] = A[A.heap_size-1]
    A.heap_size -= 1
    max_heapify(A, 0)  # try switching between 0,1
    return max_val


def heap_increase_key(A, i, new_priority):
    if new_priority < A[i].priority:
        return None
    else:
        A[i].priority = new_priority
        while i > 0 and A[parent(i)].priority < A[i].priority:
            holder = A[i]
            A[i] = A[parent(i)]
            A[parent(i)] = holder
            i = parent(i)


def max_heap_insert(A, task):
    A.heap_size += 1
    A.insert(A.heap_size - 1, task)
    A[A.heap_size - 1] = Task(task.description, float("-inf"))
    heap_increase_key(A, A.heap_size - 1, task.priority)




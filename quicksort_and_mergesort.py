import traceback
#Takes in list of Emails A, and indices p, q, and r
#Assuming that the sublists A[p...q] and A[q+1...r] are sorted by
#the length of the Email's sender, from longest to shortest,
#merges the two lists together and puts them back into the list
#Returns nothing
def merge(A,p,q,r):
    p, q, r = int(p), int(q), int(r)
    n1 = q - p + 1
    n2 = r - q
    L = [None] * (n1)
    R = [None] * (n2)
    for i in range(0, n1):
        L[i] = A[p + i]
    for j in range(0, n2):
        R[j] = A[q + 1 + j]
    i, j, k = 0, 0, p
    while i < n1 and j < n2:
        if len(L[i].sender) >= len(R[j].sender):
            A[k] = L[i]
            i += 1
        else:
            A[k] = R[j]
            j += 1
        k += 1
    while i < n1:
        A[k] = L[i]
        i += 1
        k += 1
    while j < n2:
        A[k] = R[j]
        j += 1
        k += 1

#Takes in list of Emails A, and indices p and r
#Sorts the sublist A[p...r], in order of the length of each Email's
#sender, from longest name to shortest, using Merge Sort
#Returns nothing
def merge_sort(A,p,r):
    arr = A
    l = p
    if l < r:
        # Same as (l+r)/2, but avoids overflow for
        # large l and h
        m = (l + (r - 1)) / 2

        # Sort first and second halves
        merge_sort(arr, l, m)
        merge_sort(arr, m + 1, r)
        merge(arr, l, m, r)


    #Takes in list of Emails A, and indices p and r
#Chooses A[r] as a pivot Email and re-orders the list so that
#all Emails with a longer sender name than the pivot come
#before the pivot in the list, and all Emails with a shorter
#sender name come after.
#Returns the index where the pivot is located
def partition(A,p,r):
    i = p - 1
    pi = A[r]
    for j in range(p,r):
        if len(A[j].sender) >= len(pi.sender):
            i += 1
            holder = A[i]
            A[i] = A[j]
            A[j] = holder
    holder = A[r]
    A[r] = A[i+1]
    A[i+1] = holder
    return i+1

#Takes in list of Emails A, and indices p and r
#Sorts the sublist A[p...r], in order of the length of each Email's
#sender, from longest name to shortest, using Quicksort
#Returns nothing
def quicksort(A,p,r):
    if p < r:
        q = partition(A,p,r)
        quicksort(A,p,q-1)
        quicksort(A,q+1,r)



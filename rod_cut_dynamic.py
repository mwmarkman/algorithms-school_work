import traceback


# Takes as input two parameters:

# priceList is a list of integers that represents today's cost for a single order of
#  chicken nuggets for every quantity between 0 and the length of the priceList - 1.
#  So the element at index 0 should always be 0, the element at index 1 is the cost of
#  ordering a single chicken nugget, the element at index 2 is the cost of an order of
#  two chicken nuggets, and so on.

# numNuggets is an integer, representing the total number of chicken nuggets you need to
#  order today to send the intended message to your shadow organization

# Returns a list of integers, where each integer is the number of chicken nuggets in an order,
#  and the whole list represents the lowest cost set of orders which total to numNuggets.
def optimizeNuggets(priceList, numNuggets):
    r, s = cut_rod(priceList, numNuggets)
    my_list = []
    while numNuggets > 0:
        my_list.append(s[numNuggets-1])
        numNuggets = numNuggets - s[numNuggets-1]
    return my_list


def cut_rod(priceList, numNuggets):
    r = [-1] * (numNuggets + 1)
    r[0] = 0
    s = [-1] * (numNuggets)
    s[0] = 0
    for j in range(1, numNuggets+1):
        q = 10000
        for i in range(1, j+1):
            if q > priceList[i] + r[j - i]:
                q = priceList[i] + r[j - i]
                s[j-1] = i
        r[j] = q
    return r, s


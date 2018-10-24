import turtle


#Insertion_Sort: takes one parameter, a list of Rectangle objects
#Uses insertion sort to sort the list in place from highest z value
# to lowest.
#Doesn't return anything.
def Insertion_Sort(shapels):
    for i in range(1, len(shapels)):
        done = False
        checkShape = shapels[i]
        counter = i
        while not done and counter > 0:
            if checkShape.d > shapels[counter - 1].d:
                shapels[counter] = shapels[counter - 1]
                counter -= 1
            else:
                done = True
        shapels[counter] = checkShape




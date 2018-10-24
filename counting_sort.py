
# CountingSort: takes as input a list of Student objects, and
# a character houseOrYear, which is 'h' if we are sorting the
# Students by house, or 'y' if sorting by year.
# Returns a list of Student objects, which is sorted by the
# appropriate attribute.
def CountingSort(studentList, houseOrYear):
    c_house = [0] * 4
    c_year = [0] * 8
    backup_array = studentList.copy()
    for i in range(0,len(studentList)):
        if studentList[i].house == 'Eagletalon':
            c_house[0] += 1
        elif studentList[i].house == 'Lannister':
            c_house[1] += 1
        elif studentList[i].house == 'Pufflehuff':
            c_house[2] += 1
        elif studentList[i].house == 'SNAKES':
            c_house[3] += 1
        year = studentList[i].year
        c_year[year-1] += 1
    for i in range(1,len(c_year)):
        c_year[i] += c_year[i-1]
    for i in range(1,len(c_house)):
        c_house[i] += c_house[i-1]
    if houseOrYear == 'h':
        for i in range(len(studentList),0,-1):
            the_student = studentList[i-1]
            if the_student.house == 'Eagletalon':
                the_house = 0
            elif the_student.house == 'Lannister':
                the_house = 1
            elif the_student.house == 'Pufflehuff':
                the_house = 2
            elif the_student.house == 'SNAKES':
                the_house = 3
            c_num = c_house[the_house]
            backup_array[c_num-1] = the_student
            c_house[the_house] = c_house[the_house] - 1
        return backup_array
    elif houseOrYear == 'y':
        for i in range(len(studentList),0,-1):
            the_student = studentList[i-1]
            the_year = the_student.year
            c_num = c_year[the_year-1]
            backup_array[c_num-1] = the_student
            c_year[the_year-1] = c_year[the_year-1] - 1
        print("THE FINAL ARAY IS: " + str(backup_array))
        return backup_array



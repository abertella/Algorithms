#A script to generate plots of the outputs with regression lines.
#Much of this code is based on examples found at 
#https://docs.scipy.org/doc/numpy/reference/generated/numpy.polyfit.html
#https://matplotlib.org/3.1.1/tutorials/introductory/pyplot.html
#https://docs.scipy.org/doc/scipy-0.14.0/reference/generated/scipy.stats.linregress.html
#https://matplotlib.org/3.1.0/gallery/lines_bars_and_markers/barchart.html

import numpy as np
import matplotlib.pyplot as plt
from scipy import optimize
import math

def xlogx(x, a, b, c):
    return [(a * i * math.log2(i) + b * i + c) for i in x]

file = open('lab07data.txt', 'r')

xline = file.readline().rstrip('\n').split(',')
xdata = np.array(list(map(int , xline[0:6])))
insertline = file.readline().rstrip('\n').split(',')
insertiondata = np.array(list(map(int , insertline[0:6])))
mergeline = file.readline().rstrip('\n').split(',')
mergedata = np.array(list(map(int , mergeline[0:6])))
heapline = file.readline().rstrip('\n').split(',')
heapdata = np.array(list(map(int , heapline[0:6])))
quickline = file.readline().rstrip('\n').split(',')
quickdata = np.array(list(map(int , quickline[0:6])))
bubbleline = file.readline().rstrip('\n').split(',')
bubbledata = np.array(list(map(int , bubbleline[0:6])))
selectionline = file.readline().rstrip('\n').split(',')
selectiondata = np.array(list(map(int , selectionline[0:6])))

file.close()

z, v = optimize.curve_fit(xlogx, xdata, mergedata)
q, r = optimize.curve_fit(xlogx, xdata, heapdata)
e, d = optimize.curve_fit(xlogx, xdata, quickdata)
a = np.polyfit(xdata, bubbledata, 2)
b = np.polyfit(xdata, selectiondata, 2)
w = np.polyfit(xdata, insertiondata, 2)
poly = np.poly1d(w)
poly1 = np.poly1d(a)
poly2 = np.poly1d(b)
xp = range(1, 51000, 100)
plt.plot(xdata, mergedata, "ro", label = 'MergeSort')
plt.plot(xdata, insertiondata, "bo", label = 'InsertionSort')
plt.plot(xdata, heapdata, "go", label = 'HeapSort')
plt.plot(xdata, quickdata, "yo", label = 'QuickSort')
plt.plot(xdata, selectiondata, "mo", label = 'SelectionSort')
plt.plot(xdata, bubbledata, "co", label = 'BubbleSort')
plt.plot(xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xp, poly(xp), 'b-', xp, xlogx((xp), q[0], q[1], q[2]), 'g-', xp, poly2(xp), 'm-', xp, poly1(xp), 'c-', xp, xlogx((xp), e[0], e[1], e[2]), 'y-')
plt.legend(loc = 'upper center')
plt.title("Sorter Comparison")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 51000, 0, max(insertiondata) * 1.03])
plt.show()

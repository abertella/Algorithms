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

file = open('lab05data.txt', 'r')

xdata = np.array(list(map(int , file.readline().rstrip('\n').split(','))))
insertiondata = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
mergedata = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
heapdata = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
quickdata = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
quickdatam3 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))

file2 = open('quicksortdata.txt', 'r')

line1 = file2.readline().rstrip('\n').split(',')
xdat = line1[0:10]
srsran = line1[10:]
line2 = file2.readline().rstrip('\n').split(',')
qdat = line2[0:10]
srsranq = line2[10:]
line3 = file2.readline().rstrip('\n').split(',')
qdatm3 = line3[0:10]
srsranqm3 = line3[10:]

xdataq = np.array(list(map(int , xdat)))
qdata = np.array(list(map(int , qdat)))
qdatam3 = np.array(list(map(int , qdatm3)))

file.close()
file2.close()

z, v = optimize.curve_fit(xlogx, xdata, mergedata)
q, r = optimize.curve_fit(xlogx, xdata, heapdata)
a, b = optimize.curve_fit(xlogx, xdata, quickdata)
c, d = optimize.curve_fit(xlogx, xdata, quickdatam3)
w = np.polyfit(xdata, insertiondata, 2)
poly = np.poly1d(w)
xp = range(1, 510000, 1000)
plt.plot(xdata, mergedata, "ro", label = 'MergeSort')
plt.plot(xdata, insertiondata, "bo", label = 'InsertionSort')
plt.plot(xdata, heapdata, "go", label = 'HeapSort')
plt.plot(xdata, quickdata, "mo", label = 'QuickSort')
plt.plot(xdata, quickdatam3, "co", label = 'QuickSortM3')
plt.plot(xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xp, poly(xp), 'b-', xp, xlogx((xp), q[0], q[1], q[2]), 'g-', xp, xlogx((xp), a[0], a[1], a[2]), 'm-', xp, xlogx((xp), c[0], c[1], c[2]), 'c-')
plt.legend(loc = 'upper center')
plt.title("Sorter Comparison")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(insertiondata) * 1.03])
plt.show()

a, b = optimize.curve_fit(xlogx, xdataq, qdata)
c, d = optimize.curve_fit(xlogx, xdataq, qdatam3)
plt.plot(xdataq, qdata, "mo", label = 'QuickSort')
plt.plot(xdataq, qdatam3, "co", label = 'QuickSortM3')
xp = range(1, 8200, 10)
plt.plot(xp, xlogx((xp), a[0], a[1], a[2]), 'm-', xp, xlogx((xp), c[0], c[1], c[2]), 'c-')
plt.legend(loc = 'upper center')
plt.title("QuickSort vs QuickSortM3")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 8200, 0, max(max(qdata), max(qdatam3)) * 1.03])
plt.show()


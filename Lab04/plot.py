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

file = open('output.txt', 'r')

xdata = np.array(list(map(int , file.readline().rstrip('\n').split(','))))
heapinput = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
heapsorted = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
heapreversed = np.array(list(map(int, file.readline().rstrip('\n').split(','))))

file2 = open('output1.txt', 'r')
mergedata = np.array(list(map(int, file2.readline().rstrip('\n').split(','))))
insertiondata = np.array(list(map(int, file2.readline().rstrip('\n').split(','))))

z, v = optimize.curve_fit(xlogx, xdata, heapinput)
q, r = optimize.curve_fit(xlogx, xdata, heapsorted)
s, t = optimize.curve_fit(xlogx, xdata, heapreversed)
print('z = ', z)
print('v = ', v)
print('q = ', q)
print('r = ', r)
print('s = ', s)
print('t = ', t)
xp = range(1, 510000, 100)
plt.plot(xdata, heapinput, "ro", label = 'Heap Input')
plt.plot(xdata, heapsorted, "bo", label = 'Heap Sorted')
plt.plot(xdata, heapreversed, "go", label = 'Heap Reversed')
plt.plot(xp, xlogx((xp), z[0], z[1], z[2]), 'r-')
plt.plot(xp, xlogx((xp), q[0], q[1], q[2]), 'b-')
plt.plot(xp, xlogx((xp), s[0], s[1], s[2]), 'g-')
plt.legend(loc = 'upper center')
plt.title("HeapSort")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(heapinput) * 1.03])
plt.show()

print(xdata[:5])
print(mergedata)
e, f = optimize.curve_fit(xlogx, xdata[:5], mergedata)
w = np.polyfit(xdata[:5], insertiondata, 2)
print('e = ', e)
print('f = ', v)
print ('w = ', w)
poly = np.poly1d(w)
xp = range(1, 51000, 1000)
plt.plot(xdata[:5], mergedata, "bo", label = 'MergeSort')
plt.plot(xdata[:5], insertiondata, "ro", label = 'InsertionSort')
plt.plot(xdata[:5], heapinput[:5], "go", label = 'HeapSort')
plt.plot(xp, xlogx((xp), e[0], e[1], e[2]), 'b-', xp, poly(xp), 'r-', xp, xlogx((xp), z[0], z[1], z[2]), "g-")
plt.legend(loc = 'upper center')
plt.title("Insertion vs. Merge vs. Heap")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 51000, 0, max(insertiondata) * 1.03])
plt.show()
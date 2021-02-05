#A script to generate plots of the outputs with regression lines.
#Much of this code is based on examples found at 
#https://docs.scipy.org/doc/numpy/reference/generated/numpy.polyfit.html
#https://matplotlib.org/3.1.1/tutorials/introductory/pyplot.html
#https://docs.scipy.org/doc/scipy-0.14.0/reference/generated/scipy.stats.linregress.html

import numpy as np
import matplotlib.pyplot as plt
from scipy import stats

file = open('output.txt', 'r')

x = np.array(list(map(int , file.readline().rstrip('\n').split(','))))
yinput = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
ysorted = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
yreversed = np.array(list(map(int, file.readline().rstrip('\n').split(','))))

z = np.polyfit(x, yinput, 2)
print('polyfit for input = ', z)
poly = np.poly1d(z)
xp = np.linspace(0, 500000, 10000)
plt.plot(x, yinput, "ro", xp, poly(xp), '-')
plt.title("Input Arrays")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(yinput) * 1.03])
plt.show()

print('linregress for sorted = ', stats.linregress(x, ysorted))
z = np.polyfit(x, ysorted, 1)
print('polyfit for sorted = ', z)
poly = np.poly1d(z)
plt.plot(x, ysorted, "ro", xp, poly(xp), '-')
plt.title("Sorted Arrays")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(ysorted) * 1.03])
plt.show()

z = np.polyfit(x, yreversed, 2)
print('polyfit for reversed = ', z)
poly = np.poly1d(z)
plt.plot(x, yreversed, "ro", xp, poly(xp), '-')
plt.title("Reversed Arrays")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(yreversed) * 1.03])
plt.show()
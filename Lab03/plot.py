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

file = open('Lab05', 'r')

xdata = np.array(list(map(int , file.readline().rstrip('\n').split(','))))
mergedata = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
insertiondata = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata25 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata50 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata100 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata150 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata200 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata250 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))
hybriddata300 = np.array(list(map(int, file.readline().rstrip('\n').split(','))))

z, v = optimize.curve_fit(xlogx, xdata, mergedata)
w = np.polyfit(xdata, insertiondata, 2)
print('z = ', z)
print('v = ', v)
print ('w = ', w)
poly = np.poly1d(w)
xp = range(1, 510000, 1000)
plt.plot(xdata, mergedata, "ro", label = 'MergeSort')
plt.plot(xdata, insertiondata, "bo", label = 'InsertionSort')
plt.plot(xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xp, poly(xp), 'b-')
plt.legend(loc = 'upper center')
plt.title("Insertion vs. Merge")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(insertiondata) * 1.03])
plt.show()

q, r = optimize.curve_fit(xlogx, xdata, hybriddata25)
print('q = ', q)
print('r = ', r)
plt.plot(xdata, mergedata, "ro", label = 'MergeSort') 
plt.plot(xdata, hybriddata25, "go", label = 'Hybrid25')
plt.plot(xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xp, xlogx((xp), q[0], q[1], q[2]), 'g-')
plt.legend(loc = 'upper center')
plt.title("Merge vs. Hybrid25")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(mergedata) * 1.03])
plt.show()

q, r = optimize.curve_fit(xlogx, xdata, hybriddata50)
print('q = ', q)
print('r = ', r)
plt.plot(xdata, mergedata, "ro", label = 'MergeSort') 
plt.plot(xdata, hybriddata50, "go", label = 'Hybrid50')
plt.plot(xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xp, xlogx((xp), q[0], q[1], q[2]), 'g-')
plt.legend(loc = 'upper center')
plt.title("Merge vs. Hybrid50")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(mergedata) * 1.03])
plt.show()

q, r = optimize.curve_fit(xlogx, xdata, hybriddata100)
print('q = ', q)
print('r = ', r)
plt.plot(xdata, mergedata, "ro", xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xdata, hybriddata100, "go", xp, xlogx((xp), q[0], q[1], q[2]), 'g-')
plt.title("Merge vs. Hybrid100")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(hybriddata100) * 1.03])
plt.show()

q, r = optimize.curve_fit(xlogx, xdata, hybriddata150)
print('q = ', q)
print('r = ', r)
plt.plot(xdata, mergedata, "ro", xp, xlogx((xp), z[0], z[1], z[2]), 'r-', xdata, hybriddata150, "go", xp, xlogx((xp), q[0], q[1], q[2]), 'g-')
plt.title("Merge vs. Hybrid150")
plt.xlabel("Array Size (N)")
plt.ylabel("Time (\u03BCs)")
plt.axis([0, 510000, 0, max(hybriddata150) * 1.03])
plt.show()

ind = np.arange(len(xdata))
width = 0.35

fig, ax = plt.subplots()
rects1 = ax.bar(ind - width/2, mergedata, width,
                label='Merge', color = 'r')
rects2 = ax.bar(ind + width/2, hybriddata50, width,
                label='Hybrid50', color = 'g')

ax.set_ylabel('Time (\u03BCs)')
ax.set_title('Hybrid50 vs Merge')
ax.set_xticks(ind)
ax.set_xticklabels(('100', '1000', '5000', '10000', '50000', '100000', '500000'))
ax.legend()


def autolabel(rects, xpos='center'):

    ha = {'center': 'center', 'right': 'left', 'left': 'right'}
    offset = {'center': 0, 'right': 1, 'left': -1}

    for rect in rects:
        height = rect.get_height()
        ax.annotate('{}'.format(height),
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(offset[xpos]*3, 3),
                    textcoords="offset points",
                    ha=ha[xpos], va='bottom')


autolabel(rects1, "left")
autolabel(rects2, "right")

fig.tight_layout()

plt.show()
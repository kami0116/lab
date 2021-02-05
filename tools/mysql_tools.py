import pymysql
import numpy as np
import matplotlib.pyplot as plt


def auto_label(rects, ax):
    """Attach a text label above each bar in *rects*, displaying its height."""
    for rect in rects:
        height = rect.get_height()
        ax.annotate('{}'.format(height),
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(0, 3),  # 3 points vertical offset
                    textcoords="offset points",
                    ha='center', va='bottom')


def show_profile(sql, database="test"):
    db = None
    try:
        db = pymysql.connect(
            host="tc",
            user="root",
            password="E9B69FDF118D1F46E3E358CF039B921F",
            database=database)
        cursor = db.cursor()
        cursor.execute("SET PROFILING=1")
        cursor.execute(sql)
        cursor.execute("SHOW PROFILE")
        profile = cursor.fetchall()
    finally:
        if db:
            db.close()

    plt.rcParams['font.sans-serif'] = ['SimHei']
    data = np.array(profile)
    x = np.arange(data.shape[0])
    fig, ax = plt.subplots()
    fig.set_size_inches(30, 10)
    plt.grid()
    rects = ax.bar(x, data[:, 1])
    ax.set_xticks(x)
    ax.set_xticklabels(data[:, 0])
    auto_label(rects, ax)
    ax.set_title(sql)
    plt.show()

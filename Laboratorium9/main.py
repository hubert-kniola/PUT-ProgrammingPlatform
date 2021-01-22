import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.datasets import load_boston
from sklearn.metrics import mean_squared_error, mean_absolute_error


def data_regression(dataset: pd.DataFrame) -> (pd.DataFrame, pd.Series):
    return dataset[["RM", "ZN", "CHAS", "DIS"]], dataset["MEDV"]


if __name__ == '__main__':
    boston_dataset = load_boston()
    print(boston_dataset.keys)
    df1 = pd.DataFrame(boston_dataset['data'], columns=boston_dataset['feature_names'])
    df2 = pd.DataFrame(boston_dataset['target'], columns=['MEDV'])
    data_frame = pd.concat([df1, df2], axis=1, sort=False)

    # Task 1
    print(f"First 10:\n{data_frame.head(10)}")
    print(f"Last 10:\n{data_frame.tail(10)}")
    print(' ')

    # Task 2
    print("Dataframe information:")
    data_frame.info(verbose=True)
    print(' ')
    # A. Po 7 próbek
    # B. Dane są typu float64
    # C. Nie ma brakujących wartości

    # Task 3
    print("Dataframe description:")
    print(data_frame.describe())
    print(' ')
    # A. Średni wspołczynnik wynosi 3.613524, a odchylenie standardowe 8.60
    # B. Minimalna cena wynosi 5, a maksymalna 50
    # C. Mediana wynosi 11.36

    # Task 4
    sns.set_color_codes()
    sns.histplot(data_frame['MEDV'], color="m")
    plt.savefig("./histogram.png")
    plt.clf()

    # Task 5
    sns.heatmap(data_frame.corr(), annot=True, fmt='.2f', annot_kws={"fontsize": 8})
    plt.savefig("./heatmap.png")
    plt.clf()
    # A. Silną korelację mają: RM, ZN, CHAS, DIS, B
    # B. Niepowiązany z ceną jest atrybut LSTAT
    # C. Tak istnieją, TAX - RAD

    sns.regplot(x=data_frame['MEDV'], y=data_frame['RM'])
    plt.savefig("./a_point_plot.png")
    plt.clf()

    sns.regplot(x=data_frame['MEDV'], y=data_frame['CRIM'])
    plt.savefig("./b_point_plot.png")
    plt.clf()

    sns.regplot(x=data_frame['MEDV'], y=data_frame['LSTAT'])
    plt.savefig("./c_point_plot.png")
    plt.clf()

    # D. Dodatnio skorelowane z cenami (a_point_plot)

    # Task 6
    x, y = data_regression(data_frame)
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2)

    # Task 7
    regressor = LinearRegression().fit(x_train, y_train)

    y_predicted_train = regressor.predict(x_train)
    y_predicted_test = regressor.predict(x_test)

    sns.regplot(x=y_train, y=y_predicted_train)
    plt.axis('equal')
    plt.savefig("./predicted_train.png")
    plt.clf()

    sns.regplot(x=y_test, y=y_predicted_test)
    plt.axis('equal')
    plt.savefig("./predicted_test.png")
    plt.clf()

    # Task 8
    print(f"A. Train:\tRMSE = {mean_squared_error(y_train, y_predicted_train)} MAE = {mean_absolute_error(y_train, y_predicted_train)}")
    print(f"B. Test:\tRMSE = {mean_squared_error(y_test, y_predicted_test)} MAE = {mean_absolute_error(y_test, y_predicted_test)}")


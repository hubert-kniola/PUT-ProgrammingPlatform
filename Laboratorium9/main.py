import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.datasets import load_boston
from sklearn.metrics import mean_squared_error, mean_absolute_error


def download_data() -> pd.DataFrame:
    dataset = load_boston()
    df1 = pd.DataFrame(dataset['data'], columns=dataset['feature_names'])
    df2 = pd.DataFrame(dataset['target'], columns=['MEDV'])

    return pd.concat([df1, df2], axis=1, sort=False)


def price_regression(dataset: pd.DataFrame) -> (pd.DataFrame, pd.Series):
    return dataset[["RM", "ZN", "CHAS"]], dataset["MEDV"]


if __name__ == '__main__':
    data_frame = download_data()

    # Task 1
    print(f"First 10:\n{data_frame.head(10)}")
    print(f"Last 10:\n{data_frame.tail(10)}")
    print(' ')

    # Task 2
    print("Dataframe information:")
    data_frame.info(verbose=True)
    print(' ')

    # Task 3
    print("Dataframe description:")
    print(data_frame.describe())
    print(' ')

    # Task 4
    sns.set_color_codes()
    sns.histplot(data_frame['MEDV'], color="m")
    plt.savefig("./histogram.png")
    plt.clf()

    # Task 5
    sns.heatmap(data_frame.corr(), annot=True, fmt='.2f', annot_kws={"fontsize": 8})
    plt.savefig("./heatmap.png")
    plt.clf()

    sns.regplot(x=data_frame['MEDV'], y=data_frame['RM'])
    plt.savefig("./a_point_plot.png")
    plt.clf()

    sns.regplot(x=data_frame['MEDV'], y=data_frame['CRIM'])
    plt.savefig("./b_point_plot.png")
    plt.clf()

    sns.regplot(x=data_frame['MEDV'], y=data_frame['LSTAT'])
    plt.savefig("./c_point_plot.png")
    plt.clf()

    # Task 6
    x, y = price_regression(data_frame)
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2)

    # Task 7
    regressor = LinearRegression().fit(x_train, y_train)

    y_predicted_test = regressor.predict(x_test)
    y_predicted_train = regressor.predict(x_train)

    sns.regplot(x=y_test, y=y_predicted_test)
    plt.axis('equal')
    plt.savefig("./predicted_test.png")
    plt.clf()

    sns.regplot(x=y_train, y=y_predicted_train)
    plt.axis('equal')
    plt.savefig("./predicted_train.png")
    plt.clf()

    # Task 8
    print(f"a) Test:\tMSE = {mean_squared_error(y_test, y_predicted_test)} MAE = {mean_absolute_error(y_test, y_predicted_test)}")
    print(f"b) Train:\tMSE = {mean_squared_error(y_train, y_predicted_train)} MAE = {mean_absolute_error(y_train, y_predicted_train)}")

#include <stdio.h>

void merge(int arr[], int left, int mid, int right)
{
    int temp[100];

    int i = left;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= right)
    {
        if (arr[i] <= arr[j])
        {
            temp[k] = arr[i];
            i++;
        }
        else
        {
            temp[k] = arr[j];
            j++;
        }

        k++;
    }

    while (i <= mid)
    {
        temp[k] = arr[i];
        i++;
        k++;
    }

    while (j <= right)
    {
        temp[k] = arr[j];
        j++;
        k++;
    }

    for (i = left, k = 0; i <= right; i++, k++)
    {
        arr[i] = temp[k];
    }
}

void mergeSort(int arr[], int left, int right)
{
    if (left < right)
    {
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);

        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }
}

int main()
{
    int nums[] = {5, 2, 8, 1, 3};

    int n = sizeof(nums) / sizeof(nums[0]);

    mergeSort(nums, 0, n - 1);

    printf("Sorted array: ");

    for (int i = 0; i < n; i++)
    {
        printf("%d ", nums[i]);
    }

    printf("\n");

    return 0;
}
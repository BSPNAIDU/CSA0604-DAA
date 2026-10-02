#include <stdio.h>

int main()
{
    int arr[] = {-9, 3, 4, 6, 8, 9, 10, 30};

    int n = sizeof(arr) / sizeof(arr[0]);

    int key = 10;

    int low = 0;
    int high = n - 1;

    int found = -1;

    while (low <= high)
    {
        int mid = low + (high - low) / 2;

        if (arr[mid] == key)
        {
            found = mid;
            break;
        }
        else if (arr[mid] < key)
        {
            low = mid + 1;
        }
        else
        {
            high = mid - 1;
        }
    }

    if (found != -1)
    {
        printf("Element %d is found at index %d\n", key, found);
        printf("Position = %d\n", found + 1);
    }
    else
    {
        printf("Element %d is not found\n", key);
    }

    return 0;
}
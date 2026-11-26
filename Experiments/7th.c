#include <stdio.h>

int main()
{
    int arr[] = {3, 7, 3, 5, 2, 5, 9, 2};

    int n = sizeof(arr) / sizeof(arr[0]);

    int unique[100];
    int count = 0;

    for (int i = 0; i < n; i++)
    {
        int found = 0;

        for (int j = 0; j < count; j++)
        {
            if (unique[j] == arr[i])
            {
                found = 1;
                break;
            }
        }

        if (!found)
        {
            unique[count] = arr[i];
            count++;
        }
    }

    printf("Unique elements: ");

    for (int i = 0; i < count; i++)
    {
        printf("%d ", unique[i]);
    }

    printf("\n");

    return 0;
}
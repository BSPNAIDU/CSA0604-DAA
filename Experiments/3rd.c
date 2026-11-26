#include <stdio.h>

int main()
{
    int nums[] = {1, 2, 1};
    int n = sizeof(nums) / sizeof(nums[0]);

    long long sum = 0;

    for (int i = 0; i < n; i++)
    {
        int distinct[100];
        int count = 0;

        for (int j = i; j < n; j++)
        {
            int found = 0;

            for (int k = 0; k < count; k++)
            {
                if (distinct[k] == nums[j])
                {
                    found = 1;
                    break;
                }
            }

            if (!found)
            {
                distinct[count] = nums[j];
                count++;
            }

            sum += (long long)count * count;
        }
    }

    printf("Answer = %lld\n", sum);

    return 0;
}
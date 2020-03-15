import java.io.Console;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class HelloWorld {

	private static final String HelloWorld = null;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// // 输出Hello World
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// System.out.println("EnterSomething:");
		// String data = br.readLine();
		// System.out.println("Hello world!" + data);

		// 创建要排序的数组

		int[] sortA = { 5, 28, 99, 37, 47, 1, 3, 5, 1, 6, 78, 98, 65, 56, 34, 56, 34, 56 };
		int[] sortB = null;
		int baseNum;

		// ++j 先运算++，再进行赋值；j++ 先赋值，再运算++

		// test concatAll
		int[] result = concatAll(sortA, sortB);
		String a = "";
		for (int i = 0; i < result.length; i++) {
			a += "+" + result[i];
		}
		System.out.println(a);

		// test copyOfRange
		int[] c = Arrays.copyOfRange(sortA, 1, 2);
		a = "";
		for (int i = 0; i < c.length; i++) {
			a += "+" + c[i];
		}
		System.out.println(a);

	}

	// 小于等于基准数放在左边，大于基准数放在右边
	public static int[] fastSort(int[] ar) {
		// 最好加入判断数组的长度，必须大于1
		if (ar != null && ar.length > 1) {
			// 基准数选数组的第一位
			int baseNum = ar[0];
			int[] left = null, right = null;
			int i = 0, j = ar.length;
			while (i < j) {
				for (; i < j && i < ar.length - 1;) {
					if (ar[++i] > baseNum) {
						break;
					}
				}
				for (; i < j;) {
					if (ar[--j] <= baseNum) {
						break;
					}
				}

				if (i == j) {
					// 在尾部碰到
					if (i == ar.length - 1) {
						if (ar[i] <= baseNum) { // 说明没有比基数大的数
							left = Arrays.copyOfRange(ar, 1, i + 1);
							right = null;
						} else {
							left = i == 1 ? null : Arrays.copyOfRange(ar, 1, i); // 如果i和j碰在1上，且索引1上的数比基数大，说明没有比基数小的数
							right = Arrays.copyOfRange(ar, i, i + 1); // 最后一个数比基数大
						}

					} else if (i == 1) { // 在头部碰到
						if (ar[i] <= baseNum) { // 说明没有比基数大的数，也说明j==i==1
							left = Arrays.copyOfRange(ar, 1, i + 1);
							right = null;
						} else { // 说明没有比基数小的数
							left = null;
							right = Arrays.copyOfRange(ar, 1, ar.length);
						}
					} else { // 在中间碰到
						left = Arrays.copyOfRange(ar, 1, i);
						right = Arrays.copyOfRange(ar, i, ar.length);
					}
					;

				} else {
					// 交换i和j
					int temp = ar[i];
					ar[i] = ar[j];
					ar[j] = temp;
				}
			}

			int[] base = { baseNum };

			return concatAll(fastSort(left), base, fastSort(right));
		} else {
			return ar;
		}

	}

	
	public static int[] concatAll(int[]... rest) {
		int totalLength = 0;
		for (int[] array : rest) {
			if (array != null) {
				totalLength += array.length;
			}

		}
		int[] result = new int[totalLength];

		int offset = 0;
		for (int[] array : rest) {
			if (array != null) {
				System.arraycopy(array, 0, result, offset, array.length);
				offset += array.length;
			}

		}
		return result;

	}

	// 泛型数组合并方法，有问题，无法使用
	@SafeVarargs
	public static <T> T[] concatAll(T[] first, T[]... rest) {

		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}


}

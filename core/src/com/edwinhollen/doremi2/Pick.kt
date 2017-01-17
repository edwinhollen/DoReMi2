package com.edwinhollen.doremi2

/**
 * Created by Edwin on 1/15/2017.
 */

import java.util.Random;

class Pick {
    companion object{
        val random: Random;

        init{
            random = Random();
        }

        /**
         * Picks a [T] from a list
         * @param list a list of [T]s
         * @return a [T] from the list
         */
        fun <T> from(list: List<T>): T{
            return list.get(int(list.size - 1));
        }

        /**
         * Picks a [T] from an array
         * @param array an array of [T]s
         * @return a [T] from the array
         */
        fun <T> from(array: Array<T>): T{
            return array[int(array.size - 1)];
        }

        /**
         * Picks a random [Int] between a minimum [Int] and a maximum [Int], inclusively
         * @param min the minimum [Int] (inclusive)
         * @param max the maximum [Int] (inclusive)
         * @return [Int] between [min] and [max]
         */
        fun int(min: Int, max: Int): Int{
            return random.nextInt(max + 1 - min) + min;
        }

        /**
         * Picks a random [Int] between 0 and a maximum [Int], inclusively
         * @param max the maximum [Int] (inclusive)
         * @return [Int] between 0 and [max]
         */
        fun int(max: Int): Int{
            return int(0, max);
        }

        /**
         * Picks a random [Double] between a minimum [Double] and a maximum [Double], inclusively
         * @param min the minimum [Double] (inclusive)
         * @param max the maximum [Double] (inclusive)
         * @return [Double] between [min] and [max]
         */
        fun double(min: Double, max: Double): Double{
            return min + random.nextDouble() * (max - min);
        }

        /**
         * Picks a random [Double] between 0.0 and a maximum [Double], inclusively
         * @param max the maximum [Double] (inclusive)
         * @return [Double] between 0 and [max]
         */
        fun double(max: Double): Double{
            return double(0.0, max);
        }
    }
}
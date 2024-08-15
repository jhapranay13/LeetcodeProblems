package leetcode.TreeSetTreeMap;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         You are implementing a program to use as your calendar. We can add a
 *         new event if adding the event will not cause a double booking.
 * 
 *         A double booking happens when two events have some non-empty
 *         intersection (i.e., some moment is common to both events.).
 * 
 *         The event can be represented as a pair of integers start and end that
 *         represents a booking on the half-open interval [start, end), the
 *         range of real numbers x such that start <= x < end.
 * 
 *         Implement the MyCalendar class:
 * 
 *         MyCalendar() Initializes the calendar object. boolean book(int start,
 *         int end) Returns true if the event can be added to the calendar
 *         successfully without causing a double booking. Otherwise, return
 *         false and do not add the event to the calendar.
 * 
 * 
 *         Example 1:
 * 
 *         Input ["MyCalendar", "book", "book", "book"] [[], [10, 20], [15, 25],
 *         [20, 30]] Output [null, true, false, true]
 * 
 *         Explanation MyCalendar myCalendar = new MyCalendar();
 *         myCalendar.book(10, 20); // return True myCalendar.book(15, 25); //
 *         return False, It can not be booked because time 15 is already booked
 *         by another event. myCalendar.book(20, 30); // return True, The event
 *         can be booked, as the first event takes every time less than 20, but
 *         not including 20.
 * 
 * 
 *         Constraints:
 * 
 *         0 <= start < end <= 109 At most 1000 calls will be made to book.
 *
 */

public class _729_MyCalendar1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	class MyCalendar1 {
		List< int[] > bookings;

		public MyCalendar1() {
			bookings = new ArrayList<>();    
		}

		public boolean book(int start, int end) {

			if( bookings.isEmpty() ) {
				bookings.add( new int[] { start, end } );
			} else {
				int[] first = bookings.get( 0 );
				int[] last = bookings.get( bookings.size() - 1 );

				if( end <= first[ 0 ] ) {
					bookings.add( 0, new int[] { start, end } );
				} else if( last[ 1 ] <= start ) {
					bookings.add( new int[] { start, end } );
				} else {
					int index = bsl( start );
					int nIndex = index + 1 == bookings.size() ? index : index + 1;
					int[] val = bookings.get( index );
					int[] nval = bookings.get( nIndex );

					if( val[ 1 ] <= start && end <= nval[ 0 ] ) {
						bookings.add( nIndex, new int[] {start, end} );
					} else {
						return false;
					}
				}
			}
			return true;
		}

		private int bsl( int start ) {
			int lo = 0;
			int hi = bookings.size() - 1;

			while( lo < hi ) {

				if( hi - lo == 1 ) {
					int[] first = bookings.get( lo );
					int[] last = bookings.get( hi );

					if( last[ 0 ] < start ) {
						return hi;
					} else {
						return lo;
					}
				}
				int p = lo + ( hi - lo ) / 2;
				int[] pv = bookings.get( p );

				if( pv[ 0 ] < start ) {
					lo = p;
				} else {
					hi = p - 1;
				}
			}
			return lo;
		}
	}
	//=============================================================================
	//ArrayList much faster than linkedlist
	class MyCalendar {
		List<int[]> booking;

		public MyCalendar() {
			booking = new LinkedList<>();
		}

		public boolean book(int start, int end) {

			if (booking.size() == 0) {
				booking.add(new int[] {start, end});
			} else if (booking.get(0)[0] >= end) {
				booking.add(0, new int[] {start, end});
			} else if (booking.get(booking.size() - 1)[1] <= start) {
				booking.add(new int[] {start, end});
			} else {
				int index = binarySearchForJustLessThan(start, end);
				int nextIndex = index == booking.size() - 1 ? index : index + 1;
				int[] book = booking.get(index);
				int[] nBook = booking.get(nextIndex);

				if (book[1] <= start && nBook[0] >= end) {
					booking.add(nextIndex, new int[] {start, end});
				} else {
					return false;
				}

			}
			return true;
		}

		private int binarySearchForJustLessThan(int start, int end) {
			int lo = 0;
			int hi = booking.size() - 1;

			while (lo < hi) {
				int pivot = lo + (hi - lo) / 2;

				if (hi - lo == 1) {
					if (booking.get(hi)[1] <= start) {
						return hi;
					} else {
						return lo;
					}
				}

				int[] book = booking.get(pivot);

				if (book[0] < start) {
					lo = pivot;
				} else {
					hi = pivot - 1;
				}
			}
			return hi;
		}
	}

	/**
	 * Your MyCalendar object will be instantiated and called as such:
	 * MyCalendar obj = new MyCalendar();
	 * boolean param_1 = obj.book(start,end);
	 */
	//=============================================================================================
	//TreeMap Solution
	class MyCalendar2 {
		TreeMap<Integer, Integer> holderMap;

		public MyCalendar2() {
			holderMap = new TreeMap<>();
		}

		public boolean book(int start, int end) {
			TreeMap<Integer, Integer> temp = holderMap;
			Map.Entry<Integer,Integer> floorEntry = holderMap.floorEntry(start);
			Map.Entry<Integer,Integer> ceilingEntry = holderMap.ceilingEntry(start);

			if ((floorEntry == null || floorEntry.getValue() <= start) &&
					(ceilingEntry == null || ceilingEntry.getKey() >= end)) {
				holderMap.put(start, end);
				return true;
			}
			return false;
		}
	}
}

package ru.job4j.start;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import java.util.Date;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.function.Consumer;

import ru.job4j.models.Item;

/**.
* Chapter_002
* It's class for testing behavior users
*
* @author Anton Vasilyuk
* @version 1.0
* @since 0.1
*/

public class StubInputTest {

	/**.
	 * @out it's array byte for send text
	 */
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	/**.
	 * @output it's interface consumer
	 */
	private final Consumer<String> output = new Consumer<String>() {

		/**.
		 * @stdout it's example printstream
		 */
		private final PrintStream stdout = new PrintStream(out);

		/**.
		 * Realisation main method this interface
		 * @param s is text for output
		 */
		@Override
		public void accept(String s) {
			stdout.println(s);
		}
	};

	/**.
	* @date date for getTime
	*/
	private Date date = new Date();

	/**.
	* method for write print
	* @param fileName name file
	* @return string from file
	 * @throws IOException may be exception
	*/
	private static String readUsingScanner(String fileName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
	}

	/**.
	* Test for method ADD
	*/
	@Test
	public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[] {"0", "Testing name", "desc", "y"});
		new StartUI(input, tracker, output).init();
		assertThat(tracker.findAll().get(0).getName(), is("Testing name"));
	}

	/**.
	* Test for method FINDALL
	*/
	@Test
	public void whenUserFindAllItemThenTrackerHasShowAllItemWithSameName() {
		Tracker tracker = new Tracker();
		Item item = new Item("Ivan", "desc", date.getTime());
		tracker.add(item);
		Input input = new StubInput(new String[] {"1", "y"});
		new StartUI(input, tracker, output).init();
		assertThat(tracker.findAll().get(0).getName(), is("Ivan"));
	}

	/**.
	* Test for method EDIT
	*/
	@Test
	public void whenUserEditItemThenTrackerHasEditItemById() {
		Tracker tracker = new Tracker();
		Item item = new Item("Ivan", "desc", date.getTime());
		tracker.add(item);
		item.setId("1");
		Input input = new StubInput(new String[] {"2", "1", "Egor", "desc", "y"});
		new StartUI(input, tracker, output).init();
		assertThat(tracker.findAll().get(0).getName(), is("Egor"));
	}

	/**.
	* Test for method DELETE
	*/
	@Test
	public void whenUserDeleteItemThenTrackerHasDeleteItemById() {
		Tracker tracker = new Tracker();
		Item item = new Item("Ivan", "desc", date.getTime());
		tracker.add(item);
		String itemId = item.getId();
		Input input = new StubInput(new String[] {"3", itemId, "y"});
		new StartUI(input, tracker, output).init();
		assertThat(tracker.findAll().size(), is(0));
	}

	/**.
	* Test for method FIND BY ID
	 * @throws Exception may be exception
	*/
	@Test
	public void whenUserFindByIdItemThenTrackerHasFindItemById() throws Exception {
		Tracker tracker = new Tracker();
		Item item = new Item("Ivan", "bingo", date.getTime());
		tracker.add(item);
		Input input = new StubInput(new String[] {"4", item.getId(), "y"});
		FileOutputStream f = new FileOutputStream("file.txt");
		System.setOut(new PrintStream(f));
		new StartUI(input, tracker, output).init();
		String fact = readUsingScanner("file.txt");
		assertTrue(fact.contains("bingo"));
	}

	/**.
	* Test for method FIND BY NAME
	*/
	@Test
	public void whenUserFindByNameItemThenTrackerHasFindItemByName() {
		Tracker tracker = new Tracker();
		Item item = new Item("Zoiberg", "Bingo", date.getTime());
		tracker.add(item);
		Input input = new StubInput(new String[] {"5", "Zoiberg", "y"});
		new StartUI(input, tracker, output).init();
		assertThat(tracker.findAll().get(0).getDesc(), is("Bingo"));
	}

	/**.
	 * Test on throws exception MenuOutException
	 * @throws MenuOutException it's may be exception
	 */
	@Test(expected = MenuOutException.class)
	public void whenEnterNumberInlegalThenCallMOE() throws MenuOutException {
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[] {"10", "y"});
		new StartUI(input, tracker, output).init();
	}
}
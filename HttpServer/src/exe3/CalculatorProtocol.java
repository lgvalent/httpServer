package exe3;

public class CalculatorProtocol {
	private int result = 0;

	public String processMsg(String msg){
		String parts[] = msg.split(":");
		String command = parts[0];
		int value = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;

		switch (command) {
		case "RESET": result = 0; break;
		case "ADD": result += value; break;
		case "SUB": result -= value; break;
		default:
			return "RESULT:ProtocolError. Invalid command. Cannot handle msg '" + msg + "'\r\n";
		}

		return "RESULT:" + result + "\r\n";
	}
}

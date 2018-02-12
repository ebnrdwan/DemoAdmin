package  admin.demo.ebnrdwan.demoadmin.variables;

import  admin.demo.ebnrdwan.demoadmin.lib.MessageType;

public interface IServerMessage {
	public MessageType getType();
	public String getJSONString();
}

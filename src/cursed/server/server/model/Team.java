package cursed.server.server.model;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;

import java.util.List;

import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Lists;

/**
 * 組隊控制
 */
public class Team {
	/** 組隊List*/
	private final List<PcInstance> _membersList = Lists.newList();
	/** 隊長*/
	private PcInstance _leader = null;
	/** 最大人數*/
	private int MAX_TEAM_AMOUNT = 6;
	
	public Team(){
		
		
	}
	
	/**
	 * 加入新PC到隊伍中
	 * @param pc
	 */
	public void addMember(PcInstance pc) {
		if (pc == null) {
			throw new NullPointerException();
		}
		if (((_membersList.size() == MAX_TEAM_AMOUNT))|| _membersList.contains(pc)) {
			return;
		}
        // 如果名單為空
		if (_membersList.isEmpty()) {
			setLeader(pc);
		} else {			
			// 歸類為組員
		}
		//已存在的組員
		if(!_membersList.contains(pc)){
			_membersList.add(pc);
		}
		pc.setTeam(this);
		//TODO 險是加入訊息 
		//TODO 開始更新組員資訊計時器
		
	}
	
	private void setLeader(PcInstance pc){
		_leader = pc;
	}
	
	private PcInstance getLeader(){
		return _leader;
	}
	
	public boolean isMember(PcInstance pc){
		return _membersList.contains(pc);
	}
	
	public PcInstance[] getMembers() {
		return _membersList.toArray(new PcInstance[_membersList.size()]);
	}

	public int getNumOfMembers() {
		return _membersList.size();
	}
	
	public boolean isLeader(PcInstance pc) {
		return pc.getId() == _leader.getId();
	}
	
	/**
	 * 傳遞隊長
	 * @param pc
	 */
	public void passLeader(PcInstance pc) {
		pc.getTeam().setLeader(pc);
		for (PcInstance member : getMembers()) {
			// TODO 傳送隊長封包給所有人
		}
	}
	public void broadcastAllMember(String pcID){		
		for (PcInstance member : getMembers()) {
			// TODO 傳送所有成員給new member
			if(pcID != member.getCharID()){
				/*CursedWorld.getInstance().broadcastPacketToClient(
						pcID,//回覆的對象ID
						Integer.toString(C_PartyApply)+C_PacketSymbol+
						member.getCharID()+C_PacketSymbol+
						1);*/
				CursedWorld.getInstance().broadcastPacketToClient(
						member.getCharID(),//回覆的對象ID
						Integer.toString(C_PartyApply)+C_PacketSymbol+
						pcID+C_PacketSymbol+
						1);
			}
		}
		
		
	}
	

	public void leaveMember(PcInstance pc) {
		// 導致解散的場合
		if (isLeader(pc) || (getNumOfMembers() == 2)) {
			breakup();
		} else {
			removeMember(pc);
			for (PcInstance member : getMembers()) {
				// 傳送移除封包給所有人
			}
			// TODO 傳送離開訊息給所有人
		}
	}
	
	/**
	 * 從組隊名單中移除pc
	 * @param pc
	 */
	public void removeMember(PcInstance pc) {
		if (!_membersList.contains(pc)) {
			return;
		}
		// TODO 停止更新計時器
		_membersList.remove(pc);
		pc.setTeam(null);
	}
	
	/**
	 * 解散隊伍
	 */
	public void breakup(){
		PcInstance[] members = getMembers();
		for (PcInstance member : members) {
			removeMember(member);
			//TODO 傳送解散訊息
		}
	}
}

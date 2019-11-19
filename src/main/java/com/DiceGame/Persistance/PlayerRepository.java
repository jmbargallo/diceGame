package com.DiceGame.Persistance;

import com.DiceGame.Domain.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {

    private static List<Player> players = new ArrayList<>();
    private static final String ANONYMOUS="Anonimo";

    public PlayerRepository(){}


    public void savePlayer(Player player) throws Exception {
        if (player == null)
            throw new Exception();
        if (playerExistByName(player.getName()) && !player.getName().equalsIgnoreCase(ANONYMOUS))
            throw new Exception();

        players.add(player);
    }

    public List<Player> getAllPlayers(){ return players; }

    public Player getPlayerById(int playerId) throws Exception {
        if (playerId <= 0 || !playerExistById(playerId))
            throw new Exception();
        for (Player player : players) {
            if (player.getId() == playerId)
                return player;
        }
        return null;
    }

    public boolean playerExistByName(String name) throws Exception {
        if (name == null)
            throw new Exception();
        for (Player player : players) {
            if (player.getName().equals(name))
                return true;
        }
        return false;
    }

    private boolean playerExistById(int id) throws Exception {
        if (id <= 0)
            throw new Exception();
        for (Player player : players) {
            if (player.getId() == id)
                return true;
        }
        return false;
    }

    public void deletePlayerById(int id) throws Exception {
        players.remove(getPlayerById(id));
    }
}

require_relative 'player.rb'
class RandomPlayer < Player

  def initialize(player_name, oracle, range)
    super(player_name, oracle, range)
    @guesses = 1
  end

  def no_of_guesses
    @guesses/5
  end

  def guess
    @guess = (rand(@range)+1)
    @guesses+=1

    return @guess
  end
end
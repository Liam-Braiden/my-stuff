require_relative 'oracle.rb'
require_relative 'random_player.rb'
require_relative 'smart_player.rb'
require_relative 'sequential_player.rb'
class GuessIt
  mystery_man = Oracle.new

  imreoir = RandomPlayer.new("John", mystery_man, 100)
  cliste = SmartPlayer.new("Aoife", mystery_man, 100)
  leadran = SequentialPlayer.new("Stephen", mystery_man, 100)

  @players = [imreoir,cliste,leadran]
  @finished = []
  i = 0
  while i<3
    @players.each_with_index do |player, x|
      player.guess
      if player.is_it
        @finished.push(player)
        @players.delete_at(x)
        i+=1
      end
    end
  end

  @finished.sort!{|p1, p2| p2.no_of_guesses <=> p1.no_of_guesses}

  @finished.each do |player|
    puts "#{player.player_name} guessed correctly in #{player.no_of_guesses}"
  end

  # imreoir.guess
  # imreoir.is_it
  # while !imreoir.is_it
  #   imreoir.guess
  # end
  #
  # cliste.guess
  # cliste.is_it
  # while !cliste.is_it
  #   cliste.range_to_s
  #   cliste.guess
  # end
  #
  # leadran.guess
  # leadran.is_it
  # while !leadran.is_it
  #   leadran.guess
  # end
end
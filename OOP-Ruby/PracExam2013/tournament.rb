require_relative 'player.rb'
class Tournament

  attr_reader :average_record

  def initialize(players = nil)
    @players = players.equal?(nil) ? [] : players
  end

  def add_player(player)
    @players.push(player)
  end

  def sort_players
   @players.sort!{|p1, p2| p2.points_calculator <=> p1.points_calculator}
  end

  def rounds_calculation
    sum = 0
    answers = []
    i = 0

    while i < @players.size-1
      @players.each do |p|
       sum += p.round(i)
      end
      answers[i] = sum
      sum = 0
      i+=1
    end

    answers.each do |num|
      if @players.size/num.to_i != 2
        return false
      end
    end
  end

  def consistent
    @players.each do |p|
      if @players.size-1 != p.games_played
        return "\nInconsistent in terms of games played and players in tournament"
      elsif !rounds_calculation
        return "\nGame wins and losses are in consistent"
      else
        return "\nAll data is consistent"
      end
    end
  end

  def average_record_opponents(remove=nil)
    @oppponents_record
    sum = 0
    @players.each do |player|
      sum += player.rating
    end
      sum -= remove.rating

   @oppponents_record = (sum/@players.size)
    return @oppponents_record
  end

  def average_record
    @average_record
    sum = 0
    @players.each do |player|
      sum += player.rating
    end

    @average_record = (sum/@players.size)
  end

  def to_s
    @players.each{|player| player.to_s}
  end

  def new_rating
    @players.each{|player| player.new_rating_print(average_record_opponents(player))}
  end
end
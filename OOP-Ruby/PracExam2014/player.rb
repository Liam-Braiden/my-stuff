class Player

  attr_reader :player_name

  def initialize(player_name, oracle, range)
    @player_name = player_name
    @oracle = oracle
    @range = range
    @min = 1
    @max = range
  end

  def to_s
    str = "#Name: #{@player_name} Range: #{@range}"
  end

  def values
    return @max, @min
  end

  def is_it
    if @oracle.is_it(guess) == 0           #equals
      puts "#{@player_name} guessed #{guess} and won!\n\n"
      true
    elsif @oracle.is_it(guess) == 1        #guess greater than smart players attempt
      puts "#{@player_name} guessed #{guess}"
      @min = guess + 1
      false
    elsif @oracle.is_it(guess) == -1       #guess less than smart players attempt
      puts "#{@player_name} guessed #{guess}"
      @max = guess - 1
      false
    end
  end

end
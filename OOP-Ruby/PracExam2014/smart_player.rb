class SmartPlayer < Player

  def initialize(player_name, oracle, range)
    super(player_name, oracle, range)
    @guesses = 1
  end

  def values(max, min)
    @max = max
    @min = min
  end

  def no_of_guesses
    @guesses/5
  end

  def range_to_s
    puts "Range is #{@min}..#{@max}"
  end

  def guess
    @guess = ((@min + @max)/2)
    @guesses+=1

    return @guess
  end

end
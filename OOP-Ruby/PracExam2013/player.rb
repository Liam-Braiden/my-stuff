require_relative 'tournament.rb'
class Player

  def initialize(name, rating, record)
    @name = name
    @rating = rating
    @record = record
  end
  def rating
    @rating
  end

  def points_calculator
    @record.inject(0) { |sum,val| sum += val }
  end

  def round(num)
    @record[num].to_i
  end

  def record
    str = ''
    @record.each do |res|
      str += "#{res.to_s}"
    end
    return str
  end

  def games_played
    @record.size
  end

  def expected_score(average_record)
    top = 1
    bottom = (10**((average_record-@rating)/400)+1)*1.0

    return top/bottom
  end

  def new_rating(average_record)
    @rating = (@rating+40*(points_calculator-@record.size*expected_score(average_record)))
  end

  def new_rating_print(average_record)
    new_rating = "#{@name}\t  new:#{new_rating(average_record)}\t  #{record}\t  #{points_calculator} points\n"
    return new_rating
  end

  def to_s
    str = "#{@name}\t  new #{@rating}\t  #{record}\t  #{points_calculator} points\n"
    return str
  end



end
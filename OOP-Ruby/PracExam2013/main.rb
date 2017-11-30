require_relative 'tournament.rb'
require_relative 'player.rb'

tournament = Tournament.new
begin
  IO.foreach('tournament.txt') do |line|
    data = line.split
    name = data[0]
    rating = data[1].to_i
    record = []
    data[2..data.length].each_with_index {|result,i | record[i] = result.to_i}

    tournament.add_player(Player.new(name, rating, record))
  end
end rescue abort('error reading tournament.txt')

puts "Average rating is : #{tournament.average_record}"
puts tournament.to_s
tournament.sort_players
puts "\nSORTED:\n"
puts tournament.to_s

puts tournament.consistent

puts tournament.new_rating
puts "Average rating is : #{tournament.average_record}"


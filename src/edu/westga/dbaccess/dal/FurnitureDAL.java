package edu.westga.dbaccess.dal;

import java.util.HashSet;
import edu.westga.dbaccess.model.Furniture;
/**
* The furniture dal
*
* @author Jamie Echols
* @version Fall 2021
**/
public class FurnitureDAL {
	/**
	 * Gets a list of furniture by their style and category
	 * 
	 * @param styleIds the list of style Ids
	 * @param categoryIds the list of category ids 
	 *
	 * @return a hashset of furniture

	 * @throws SQLException
	 */
	 public HashSet<Furniture> getFurnitureByCriteria(List<Integer> styleIds, List<Integer> categoryIds) throws SQLException {
		HashSet<Furniture> furniture = new HashSet<Furniture>();
		String styleQuery = "select furnitureId, price, styleId, categoryId, quantity from furniture where style=?";
		String categoryQuery = "select  furnitureId, price, styleId, categoryId, quantity from furniture where category=?";
		for (Integer styleId : styleIds) {
			try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(styleQuery)) {

				stmt.setString(1, styleId);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					furniture.add(new Furniture(rs.getInt("furnitureId")), rs.getDouble("price"), rs.getInt("styleId"),
						rs.getInt("categoryId"), rs.getInt("quantity"));
				}

			}
		}

		for (Integer categoryId : categoryIds) {
			try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(styleQuery)) {

				stmt.setString(1, styleId);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					furniture.add(new Furniture(rs.getInt("furnitureId")), rs.getDouble("price"), rs.getInt("styleId"),
						rs.getInt("categoryId"), rs.getInt("quantity"));
				}

			}
		}
		return furniture;
	 }
}
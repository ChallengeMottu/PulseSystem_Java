package pulse.com.br.pulse.infraestructure.configurations;

public class Mapper {

    public static <D, E> E toEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();


            for (var fieldDTO : dto.getClass().getDeclaredFields()) {
                fieldDTO.setAccessible(true);
                try {
                    var fieldEntity = entityClass.getDeclaredField(fieldDTO.getName());
                    fieldEntity.setAccessible(true);
                    fieldEntity.set(entity, fieldDTO.get(dto));
                } catch (NoSuchFieldException ignored) {

                }
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao mapear DTO para Entity", e);
        }
    }


    public static <E, D> D toDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();

            for (var fieldEntity : entity.getClass().getDeclaredFields()) {
                fieldEntity.setAccessible(true);
                try {
                    var fieldDTO = dtoClass.getDeclaredField(fieldEntity.getName());
                    fieldDTO.setAccessible(true);
                    fieldDTO.set(dto, fieldEntity.get(entity));
                } catch (NoSuchFieldException ignored) {}
            }
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao mapear Entity para DTO", e);
        }
    }
}

